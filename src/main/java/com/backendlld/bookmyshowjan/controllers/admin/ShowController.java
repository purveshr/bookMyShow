package com.backendlld.bookmyshowjan.controllers.admin;

import com.backendlld.bookmyshowjan.dtos.CreateShowRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateShowResponseDTO;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.services.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        Long ownerId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        CreateShowResponseDTO response = showService.createShow(request, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
