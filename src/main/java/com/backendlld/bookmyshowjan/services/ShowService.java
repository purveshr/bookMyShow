package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.CreateShowRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateShowResponseDTO;
import com.backendlld.bookmyshowjan.exception.ShowConflictException;
import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.models.Theater;
import com.backendlld.bookmyshowjan.repos.MovieRepository;
import com.backendlld.bookmyshowjan.repos.ScreenRepository;
import com.backendlld.bookmyshowjan.repos.ShowRepository;
import com.backendlld.bookmyshowjan.repos.TheaterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    public CreateShowResponseDTO createShow(CreateShowRequestDTO request, Integer ownerId) {

        // 1. Fetch movie to get duration
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // 2. Fetch screen
        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        // 3. Check for overlapping shows
        checkForOverlap(request.getScreenId(), request.getDate(),
                request.getTime(), movie.getDurationMins());

        // 4. Create and save show
        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setDate(request.getDate());
        show.setTime(request.getTime());

        Show savedShow = showRepository.save(show);

        // 5. Build response
        CreateShowResponseDTO response = new CreateShowResponseDTO();
        response.setShowId(savedShow.getId());
        response.setMovieId(movie.getId());
        response.setScreenId(screen.getId());
        response.setTheaterId(screen.getTheater().getId());
        show.setTheater(screen.getTheater());
        response.setDate(savedShow.getDate());
        response.setTime(savedShow.getTime());

        return response;
    }

    private void checkForOverlap(Integer screenId, Date date, Time startTime,
                                 Integer movieDuration) {

        List<Show> existingShows = showRepository.findByScreenAndDate(screenId, date);

        LocalTime newShowStart = startTime.toLocalTime();
        LocalTime newShowEnd = newShowStart.plusMinutes(movieDuration);

        for (Show existingShow : existingShows) {
            LocalTime existingStart = existingShow.getTime().toLocalTime();
            LocalTime existingEnd = existingStart.plusMinutes(
                    existingShow.getMovie().getDurationMins()
            );

            if (isOverlapping(newShowStart, newShowEnd, existingStart, existingEnd)) {
                throw new ShowConflictException(
                        String.format("Show timing conflict! Another show runs from %s to %s",
                                existingStart, existingEnd)
                );
            }
        }
    }

    // Two time ranges overlap if: start1 < end2 AND start2 < end1
    private boolean isOverlapping(LocalTime start1, LocalTime end1,
                                  LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public List<CreateShowResponseDTO> getShowsByTheaterAndDate(Integer theaterId, Date date) {
        List<Show> shows = showRepository.findByTheater_IdAndDate(theaterId, date);

        return shows.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CreateShowResponseDTO convertToDTO(Show show) {
        return new CreateShowResponseDTO(
                show.getMovie().getId(),
                show.getTheater().getId(),
                show.getScreen().getId(),
                show.getDate(),
                show.getTime(),
                show.getId()
        );
    }
}