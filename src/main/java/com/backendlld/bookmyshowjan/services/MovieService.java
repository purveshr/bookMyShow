package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.MovieRequestDTO;
import com.backendlld.bookmyshowjan.dtos.MovieResponseDTO;
import com.backendlld.bookmyshowjan.dtos.MovieTitleDirectorDTO;
import com.backendlld.bookmyshowjan.exception.DuplicateMovieException;
import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.repos.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {   // inject repo
        this.movieRepository = movieRepository;
    }

    public MovieResponseDTO createMovie(MovieRequestDTO request) {

        boolean exists = movieRepository
                .existsByTitleAndDirector(request.getTitle(), request.getDirector());
        if (exists) {
            throw new DuplicateMovieException("Movie with same title and director already exists");
        }

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setDirector(request.getDirector());
        movie.setGenre(request.getGenre());
        movie.setDescription(request.getDescription());
        movie.setDurationMins(request.getDurationMins());
        movie.setRating(request.getRating());
        Movie saved = movieRepository.save(movie);
        return new MovieResponseDTO(
                saved.getTitle(),
                saved.getId(),
                saved.getDirector(),
                saved.getGenre(),
                saved.getDescription()
        );
    }

    public List<MovieTitleDirectorDTO> titleDirectors() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream()
                .map(movie -> new MovieTitleDirectorDTO(movie.getTitle(), movie.getDirector()))
                .toList();
    }

    public List<MovieTitleDirectorDTO> geners(String genes) {
        List<Movie> movies = movieRepository.findByGenreContainingIgnoreCase(genes);
        return movies.stream()
                .map(movie -> new MovieTitleDirectorDTO(movie.getTitle(), movie.getDirector()))
                .toList();
    }
}
