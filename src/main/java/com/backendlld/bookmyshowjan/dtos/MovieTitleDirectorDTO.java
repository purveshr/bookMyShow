package com.backendlld.bookmyshowjan.dtos;



public class MovieTitleDirectorDTO {
    String title;
    String director;

    public MovieTitleDirectorDTO(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public String getTitle() { return title; }
    public String getDirector() { return director; }

}
