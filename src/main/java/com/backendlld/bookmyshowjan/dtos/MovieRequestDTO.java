package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieRequestDTO {
    private String title;
    private LocalDate releaseDate;
    private String director;
    private String genre;
    private String description;
    private int durationMins;
    private Integer rating;
}
