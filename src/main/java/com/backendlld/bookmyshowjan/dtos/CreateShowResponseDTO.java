package com.backendlld.bookmyshowjan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShowResponseDTO {
    private Integer movieId;
    private Integer theaterId;
    private Integer screenId;
    private Date date;
    private Time time;
    private Integer showId;
}
