package com.backendlld.bookmyshowjan.controllers.admin;


import com.backendlld.bookmyshowjan.dtos.TheaterRequestDTO;
import com.backendlld.bookmyshowjan.dtos.TheaterResponseDTO;
import com.backendlld.bookmyshowjan.services.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
