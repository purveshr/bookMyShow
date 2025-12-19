package com.backendlld.bookmyshowjan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

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
