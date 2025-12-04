package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.TheaterRequestDTO;
import com.backendlld.bookmyshowjan.dtos.TheaterResponseDTO;
import com.backendlld.bookmyshowjan.models.Theater;
import com.backendlld.bookmyshowjan.repos.TheaterRepository;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public TheaterResponseDTO createTheater(TheaterRequestDTO request) {
        Theater theater = new Theater();
        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());

        Theater saved = theaterRepository.save(theater);

        TheaterResponseDTO response = new TheaterResponseDTO();
        response.setName(saved.getName());
        response.setTheaterId(saved.getId());   // BaseModel.id (UUID/String)
        return response;
    }

}
