package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Theater;
import com.backendlld.bookmyshowjan.repos.ScreenRepository;
import com.backendlld.bookmyshowjan.repos.TheaterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;

    public ScreenService(ScreenRepository screenRepository,
                         TheaterRepository theaterRepository) {
        this.screenRepository = screenRepository;
        this.theaterRepository = theaterRepository;
    }

    @Transactional
    public Screen createScreen(Integer theaterId, String name) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new IllegalArgumentException("Theater not found: " + theaterId));

        screenRepository.findByNameAndTheaterId(name, theaterId)
                .ifPresent(existing -> {
                    throw new RuntimeException("Screen with requested name is already present.");
                });

        Screen screen = new Screen();
        screen.setName(name);
        screen.setTheaterName(theater.getName());
        screen.setTheaterId(theater.getId());
        screen.setTheater(theater);
        theater.getScreens().add(screen);

        return screenRepository.save(screen);
    }
}
