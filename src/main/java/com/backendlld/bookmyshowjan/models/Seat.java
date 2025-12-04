package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatName;
    private int rowVal;
    private int columnVal;
    @ManyToOne
    private SeatType seatType;

    @ManyToOne
    private Screen screen;
}
// Seat Many : 1 SeatType