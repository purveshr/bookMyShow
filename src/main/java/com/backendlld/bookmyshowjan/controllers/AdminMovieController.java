package com.backendlld.bookmyshowjan.controllers;

import com.backendlld.bookmyshowjan.dtos.CreateMovieDTO;
import com.backendlld.bookmyshowjan.dtos.MovieDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminMovieController {

    // Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void createMovie(@RequestBody CreateMovieDTO request) { /* ... */ }

}
