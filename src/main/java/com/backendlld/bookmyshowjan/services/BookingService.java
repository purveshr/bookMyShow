package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.dtos.CreateBookingRequestDTO;
import com.backendlld.bookmyshowjan.dtos.CreateBookingResponseDTO;
import com.backendlld.bookmyshowjan.models.*;
import com.backendlld.bookmyshowjan.repos.BookingRepository;
import com.backendlld.bookmyshowjan.repos.ShowRepository;
import com.backendlld.bookmyshowjan.repos.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    public BookingService(BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional
    public CreateBookingResponseDTO bookSeats(CreateBookingRequestDTO request, Integer userId) {

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new IllegalArgumentException("Show not found: " + request.getShowId()));

        List<ShowSeat> seats = showSeatRepository.findAllById(request.getShowSeatIds());

        if (seats.size() != request.getShowSeatIds().size()) {
            throw new IllegalArgumentException("Some seats not found");
        }

        // Ensure all belong to same show
        boolean wrongShow = seats.stream()
                .anyMatch(s -> s.getShow().getId() != show.getId());

        if (wrongShow) {
            throw new IllegalArgumentException("Seats do not belong to the given show");
        }

        // Check availability
        boolean anyNotAvailable = seats.stream()
                .anyMatch(s -> s.getStatus() != ShowSeatStatus.AVAILABLE);
        if (anyNotAvailable) {
            throw new IllegalArgumentException("One or more seats are not available");
        }

        // Block seats
        Date now = new Date();
        seats.forEach(s -> {
            s.setStatus(ShowSeatStatus.BLOCKED);
            s.setBlockedAt(now);
        });

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setShowSeats(seats);
        booking.setBookingStatus(BookingStatus.PENDING);

        Booking saved = bookingRepository.save(booking);

        CreateBookingResponseDTO resp = new CreateBookingResponseDTO();
        resp.setBookingId(saved.getId());
        resp.setStatus(saved.getBookingStatus());
        resp.setShowId(show.getId());
        resp.setShowSeatIds(
                seats.stream().map(ShowSeat::getId).toList()
        );

        return resp;
    }
}
