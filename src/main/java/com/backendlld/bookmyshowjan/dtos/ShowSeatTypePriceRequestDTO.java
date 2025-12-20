package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatTypePriceRequestDTO {
    private String seatTypeName;   // "RECLINER", "PLATINUM", "GOLD"
    private Integer priceInRupees; // e.g. 600, 400, 250
}