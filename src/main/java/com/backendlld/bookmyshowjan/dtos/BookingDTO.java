package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDTO {
    private Long id;
    private String bookingInfo;

    public BookingDTO() {}

    public BookingDTO(Long id, String bookingInfo) {
        this.id = id;
        this.bookingInfo = bookingInfo;
    }
}
