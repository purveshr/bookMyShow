package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.CreateShowRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateShowResponseDTO;
import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.models.Theater;
import com.backendlld.bookmyshowjan.repos.MovieRepository;
import com.backendlld.bookmyshowjan.repos.ScreenRepository;
import com.backendlld.bookmyshowjan.repos.ShowRepository;
import com.backendlld.bookmyshowjan.repos.TheaterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowService(ShowRepository showRepository,
                       TheaterRepository theaterRepository,
                       MovieRepository movieRepository,
                       ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.theaterRepository = theaterRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    public CreateShowResponseDTO createShow(CreateShowRequestDTO request, Integer ownerId) {
        // Validate theater ownership
        Theater theater = theaterRepository.findById(request.getTheaterId())
                .orElseThrow(() -> new EntityNotFoundException("Theater not found"));
        if (!theater.getOwnerId().equals(ownerId)) {
            throw new AccessDeniedException("Not authorized for this theater");
        }

        // Fetch entities by ID
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new EntityNotFoundException("Screen not found"));

        // Check show conflict
        if (showRepository.existsByScreenIdAndDateAndTime(
                screen.getId(), request.getDate(), request.getTime())) {
            throw new ConflictException("Show already exists at this time");
        }

        Show show = new Show();
        show.setMovie(movie);
        show.setTheater(theater);
        show.setScreen(screen);
        show.setDate(request.getDate());
        show.setTime(request.getTime());

        Show savedShow = showRepository.save(show);

        return new CreateShowResponseDTO(
                movie.getId(), theater.getId(), screen.getId(),
                request.getDate(), request.getTime(), savedShow.getId()
        );
    }
}