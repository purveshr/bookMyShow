package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;

    // JPA relation
    @ManyToOne
    @JoinColumn(name = "theater_id")   // FK column
    private Theater theater;

    // Snapshot fields – use different physical column names
    @Column(name = "theater_id_snapshot")
    private Integer theaterId;          // copy of theater.getId()

    @Column(name = "theater_name_snapshot")
    private String theaterName;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    public void setSeats(List<Seat> seats) { this.seats = seats; }
}