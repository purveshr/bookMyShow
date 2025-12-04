package com.backendlld.bookmyshowjan.controllers.admin;

import com.backendlld.bookmyshowjan.dtos.MovieRequestDTO;
import com.backendlld.bookmyshowjan.dtos.MovieResponseDTO;
import com.backendlld.bookmyshowjan.exception.DuplicateMovieException;
import com.backendlld.bookmyshowjan.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/movie")
public class AdminMovieController { //

    public AdminMovieController(MovieService movieService) {   // inject service
        this.movieService = movieService;
    }

    private final MovieService movieService;
    // Admin only
    @PostMapping("/add") // admin/movie.add
    @PreAuthorize("hasRole('ADMIN')") // /admin/movie/addMovie
    public ResponseEntity<?> createMovie(@RequestBody MovieRequestDTO request) {
        try {
            MovieResponseDTO response = movieService.createMovie(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateMovieException ex) {
            Map<String, String> body = Map.of(
                    "error", "DUPLICATE_MOVIE",
                    "message", ex.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
    }
}
