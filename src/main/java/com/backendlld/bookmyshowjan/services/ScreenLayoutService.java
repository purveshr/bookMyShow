package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.SeatBlockConfig;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Seat;
import com.backendlld.bookmyshowjan.models.SeatType;
import com.backendlld.bookmyshowjan.repos.ScreenRepository;
import com.backendlld.bookmyshowjan.repos.SeatRepository;
import com.backendlld.bookmyshowjan.repos.SeatTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenLayoutService {
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;

    public ScreenLayoutService(ScreenRepository screenRepository,
                               SeatRepository seatRepository,
                               SeatTypeRepository seatTypeRepository) {
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.seatTypeRepository = seatTypeRepository;
    }

    @Transactional
    public Screen generateLayout(Integer screenId, List<SeatBlockConfig> blocks) {
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("Screen not found: " + screenId));

        // Clear existing seats through the entity collection
        if (screen.getSeats() != null) {
            screen.getSeats().clear();   // orphanRemoval will delete them
        }

        List<Seat> newSeats = new ArrayList<>();

        for (SeatBlockConfig block : blocks) {
            SeatType seatType = seatTypeRepository
                    .findByNameIgnoreCase(block.seatTypeName())
                    .orElseThrow(() -> new IllegalArgumentException("SeatType not found: " + block.seatTypeName()));

            for (int r = block.startRow(); r <= block.endRow(); r++) {
                for (int c = block.startCol(); c <= block.endCol(); c++) {
                    String positionKey = String.format("R%02dC%02d", r, c);
                    if (block.holes().contains(positionKey)) {
                        continue;
                    }

                    Seat seat = new Seat();
                    seat.setRowVal(r);
                    seat.setColumnVal(c);
                    seat.setSeatName(positionKey);
                    seat.setSeatType(seatType);
                    seat.setScreen(screen);     // owning side

                    newSeats.add(seat);
                }
            }
        }

        // Add to existing collection instead of replacing it
        if (screen.getSeats() == null) {
            screen.setSeats(new ArrayList<>());
        }
        screen.getSeats().addAll(newSeats);

        // No need for explicit deleteByScreenId; orphanRemoval + clear() handled it
        return screenRepository.save(screen);
    }
}
