package com.backendlld.bookmyshowjan.dtos;

import com.backendlld.bookmyshowjan.models.Movie;
import com.backendlld.bookmyshowjan.models.Screen;
import com.backendlld.bookmyshowjan.models.Theater;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Date;


@Getter @Setter
public class CreateShowRequestDTO {
    private Integer movieId;
    private Integer theaterId;
    private Integer screenId;
    private Date date;
    private Time time;
}