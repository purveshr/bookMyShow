package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.ShowSeatResponseDTO;
import com.backendlld.bookmyshowjan.models.Seat;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.models.ShowSeatType;
import com.backendlld.bookmyshowjan.repos.ShowRepository;
import com.backendlld.bookmyshowjan.repos.ShowSeatTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatLayoutService {
    private final ShowRepository showRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public ShowSeatLayoutService(ShowRepository showRepository,
                                 ShowSeatTypeRepository showSeatTypeRepository) {
        this.showRepository = showRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    @Transactional
    public List<ShowSeatResponseDTO> getSeatsForShow(Integer showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found: " + showId));

        // Screen has physical seats; Show adds price and booking state
        List<Seat> seats = show.getScreen().getSeats();

        return seats.stream()
                .map(seat -> {
                    String seatTypeName = seat.getSeatType().getName();

                    ShowSeatType mapping = showSeatTypeRepository
                            .findByShowIdAndSeatTypeNameIgnoreCase(showId, seatTypeName)
                            .orElseThrow(() -> new IllegalStateException(
                                    "Price not configured for show "
                                            + showId + " and type " + seatTypeName));

                    ShowSeatResponseDTO dto = new ShowSeatResponseDTO();
                    dto.setSeatId(seat.getId());
                    dto.setSeatName(seat.getSeatName());
                    dto.setRow(seat.getRowVal());
                    dto.setCol(seat.getColumnVal());
                    dto.setSeatType(seatTypeName);
                    dto.setPriceInRupees(mapping.getPriceInRupees());
                    return dto;
                })
                .toList();
    }
}
