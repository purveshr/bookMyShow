package com.backendlld.bookmyshowjan.controllers.admin;

import com.backendlld.bookmyshowjan.dtos.ShowSeatTypePriceRequestDTO;
import com.backendlld.bookmyshowjan.models.SeatType;
import com.backendlld.bookmyshowjan.models.Show;
import com.backendlld.bookmyshowjan.models.ShowSeatType;
import com.backendlld.bookmyshowjan.repos.SeatTypeRepository;
import com.backendlld.bookmyshowjan.repos.ShowRepository;
import com.backendlld.bookmyshowjan.repos.ShowSeatTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/showPrice")
public class AdminShowPriceController {
    private final ShowRepository showRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public AdminShowPriceController(ShowRepository showRepository,
                                    SeatTypeRepository seatTypeRepository,
                                    ShowSeatTypeRepository showSeatTypeRepository) {
        this.showRepository = showRepository;
        this.seatTypeRepository = seatTypeRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    @PostMapping("/{showId}/seat-type-prices")
    public ResponseEntity<Void> setSeatTypePrices(
            @PathVariable String showId,
            @RequestBody List<ShowSeatTypePriceRequestDTO> body) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found: " + showId));

        for (ShowSeatTypePriceRequestDTO dto : body) {
            SeatType seatType = seatTypeRepository
                    .findByNameIgnoreCase(dto.getSeatTypeName())
                    .orElseThrow(() -> new IllegalArgumentException("SeatType not found: " + dto.getSeatTypeName()));

            ShowSeatType mapping = showSeatTypeRepository
                    .findByShowIdAndSeatTypeNameIgnoreCase(showId, dto.getSeatTypeName())
                    .orElseGet(() -> {
                        ShowSeatType sst = new ShowSeatType();
                        sst.setShow(show);
                        sst.setSeatType(seatType);
                        return sst;
                    });

            mapping.setPrice(dto.getPriceInCents());
            showSeatTypeRepository.save(mapping);
        }

        return ResponseEntity.ok().build();
    }
}
