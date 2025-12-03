package com.backendlld.bookmyshowjan.dtos;

import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Theater;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class CreateShowDTO {
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private Date date;
}
