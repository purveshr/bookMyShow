package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theater theater;
    @ManyToOne
    private Screen screen;
    private Date date;
    @OneToMany
    private List<ShowSeat> seats;
    @OneToMany
    private List<ShowSeatType> showSeatTypes;
}
