package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDTO {
    private Integer showId;
    private List<Integer> showSeatIds;
}
