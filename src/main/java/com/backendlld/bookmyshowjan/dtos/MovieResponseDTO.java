package com.backendlld.bookmyshowjan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    private String title;
    private Integer id;
    private String director;
    private String genre;
    private String description;
}
