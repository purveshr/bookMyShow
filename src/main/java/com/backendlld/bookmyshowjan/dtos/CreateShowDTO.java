package com.backendlld.bookmyshowjan.dtos;

import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Theatre;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class CreateShowDTO {
    private Movie movie;
    private Theatre theatre;
    private Screen screen;
    private Date date;
}
