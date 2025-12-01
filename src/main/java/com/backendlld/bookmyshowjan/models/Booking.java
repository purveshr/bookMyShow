package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @ManyToOne
    private User user;
    // if a booking is cancelled , then the same showSeat might be booked by someone else
    // ManyToMany
    @OneToMany
    private List<ShowSeat> showSeats;
    private Date bookingDate;
    @OneToMany
    private List<Payment> payments;
    @ManyToOne
    private Show show;
    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;
}


