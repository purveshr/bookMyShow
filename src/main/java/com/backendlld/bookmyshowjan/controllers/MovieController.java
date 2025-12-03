package com.backendlld.bookmyshowjan.controllers;


import com.backendlld.bookmyshowjan.dtos.MovieTitleDirectorDTO;
import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.repos.MovieRepository;
import com.backendlld.bookmyshowjan.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    // GET All Movies
    @GetMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MovieTitleDirectorDTO>>  getMovies() {
        List<MovieTitleDirectorDTO> response = movieService.titleDirectors();

        if(response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET movie by ID
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        Movie movie = movieRepository.findByMovieId(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    // GET movies by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieTitleDirectorDTO>> getMoviesByGenre(@PathVariable String genre) {
        List<MovieTitleDirectorDTO> response = movieService.geners(genre);

        if(response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
