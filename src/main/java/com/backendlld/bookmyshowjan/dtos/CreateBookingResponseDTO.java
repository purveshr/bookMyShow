package com.backendlld.bookmyshowjan.dtos;

import com.backendlld.bookmyshowjan.models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingResponseDTO {
    private Integer bookingId;
    private BookingStatus status;
    private Integer showId;
    private List<Integer> showSeatIds;
}
