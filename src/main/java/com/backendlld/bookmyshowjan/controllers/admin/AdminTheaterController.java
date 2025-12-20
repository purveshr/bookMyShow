package com.backendlld.bookmyshowjan.controllers.admin;


import com.backendlld.bookmyshowjan.dtos.TheaterRequestDTO;
import com.backendlld.bookmyshowjan.dtos.TheaterResponseDTO;
import com.backendlld.bookmyshowjan.dtos.UpdateTheaterOwnerRequestDTO;
import com.backendlld.bookmyshowjan.services.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/theater")
public class AdminTheaterController {

    private final TheaterService theaterService;

    public AdminTheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/add") // "/admin/theater/add"
    public ResponseEntity<TheaterResponseDTO> createTheater(@RequestBody TheaterRequestDTO request){
        return ResponseEntity.ok(theaterService.createTheater(request));
    }

    @PutMapping("/{theaterId}/owner")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTheaterOwner(
            @PathVariable Integer theaterId,
            @RequestBody UpdateTheaterOwnerRequestDTO request) {

        theaterService.updateTheaterOwner(theaterId, request.getOwnerId());
        return ResponseEntity.ok(Map.of(
                "message", "Owner updated successfully",
                "theaterId", theaterId,
                "ownerId", request.getOwnerId()
        ));
    }

}
