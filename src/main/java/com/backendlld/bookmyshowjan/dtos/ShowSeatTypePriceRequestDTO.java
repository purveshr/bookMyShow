package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatTypePriceRequestDTO {
    private String seatTypeName;  // "GOLD"
    private int priceInCents;
}
