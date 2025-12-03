package com.backendlld.bookmyshowjan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieResponseDTO {
    private String title;
    private String Id;
    private String director;
    private String genre;
    private String description;
}
