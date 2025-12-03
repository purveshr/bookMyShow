package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    @ManyToOne
    private Theater theatre;
    @OneToMany
    private List<Seat> seats;
}


// Booking Ticket : Completed by you

// Sign Up User
// Email , password
// create the user
