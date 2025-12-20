package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theater theater;
    @ManyToOne
    private Screen screen;
    private Date date;
    private Time time;
    @OneToMany
    private List<ShowSeat> seats;
    @OneToMany(mappedBy = "show", orphanRemoval = true)
    private List<ShowSeatType> showSeatTypes;
}
