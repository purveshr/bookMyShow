package com.backendlld.bookmyshowjan.controllers;

import com.backendlld.bookmyshowjan.dtos.CreateBookingRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateBookingResponseDTO;
import com.backendlld.bookmyshowjan.security.CustomUserDetails;
import com.backendlld.bookmyshowjan.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CreateBookingResponseDTO> book(
            @RequestBody CreateBookingRequestDTO request,
            Authentication authentication) {

        Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        CreateBookingResponseDTO response = bookingService.bookSeats(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
