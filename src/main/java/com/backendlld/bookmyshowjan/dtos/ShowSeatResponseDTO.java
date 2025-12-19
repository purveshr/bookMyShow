package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatResponseDTO {
    private Integer seatId;
    private String seatName;
    private int row;
    private int col;
    private String seatType;
    private int priceInCents;
}
