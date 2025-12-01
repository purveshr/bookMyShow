package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private int price;
}

// 1 Gold 300
// 1 Silver 250
// 1 Bronze 200
// 2 Gold 550
// 2 Silver 400
