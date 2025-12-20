package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus status;
    private Date blockedAt;
}

//
// 1 A1 Available
// 1 A2 Booked
// 1 A3 Booked
// 2 A1 Booked
// 2 A2 Blocked
// 2 A3 OUT_OF_SERVICE

