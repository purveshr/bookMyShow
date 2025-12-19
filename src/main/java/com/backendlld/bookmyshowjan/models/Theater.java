package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater extends BaseModel {
    private String name;
    private String address;
    private String city;
    private Integer ownerId;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screens;
    // Constructors, getters, setters
    public void addScreen(Screen screen) {
        screens.add(screen);
        screen.setTheater(this);
    }
}
