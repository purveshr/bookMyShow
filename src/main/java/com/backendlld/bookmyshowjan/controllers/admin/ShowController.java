package com.backendlld.bookmyshowjan.controllers.admin;

import com.backendlld.bookmyshowjan.dtos.CreateShowRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateShowResponseDTO;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.security.CustomUserDetails;
import com.backendlld.bookmyshowjan.services.ShowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//import java.sql.Date;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    @PreAuthorize("hasRole('THEATER_OWNER')")
    public ResponseEntity<CreateShowResponseDTO> createShow(
            @RequestBody CreateShowRequestDTO request,
            Authentication authentication) {

        Integer ownerId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        CreateShowResponseDTO response = showService.createShow(request, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<CreateShowResponseDTO>> getShowsByTheater(
            @PathVariable Integer theaterId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        List<CreateShowResponseDTO> shows = showService.getShowsByTheaterAndDate(theaterId, date);
        return ResponseEntity.ok(shows);
    }
}
