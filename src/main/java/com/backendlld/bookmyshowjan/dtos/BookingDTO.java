package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDTO {
    private Integer id;
    private String bookingInfo;

    public BookingDTO() {}

    public BookingDTO(Integer id, String bookingInfo) {
        this.id = id;
        this.bookingInfo = bookingInfo;
    }
}
