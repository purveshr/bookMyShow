package com.backendlld.bookmyshowjan.controllers;

import com.backendlld.bookmyshowjan.dtos.CreateShowDTO;
import com.backendlld.bookmyshowjan.dtos.ShowDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    // Theater owners only
    @PostMapping("/shows")
    @PreAuthorize("hasRole('THEATER_OWNER')")
    public void createShow(@RequestBody CreateShowDTO request) {
        //
    }

}
