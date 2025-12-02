package com.backendlld.bookmyshowjan.controllers;


import com.backendlld.bookmyshowjan.dtos.MovieDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @GetMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public String getMovies() {
        return "HAHK";
    }
}
