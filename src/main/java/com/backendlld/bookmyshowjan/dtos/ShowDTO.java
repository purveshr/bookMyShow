package com.backendlld.bookmyshowjan.dtos;

import com.backendlld.bookmyshowjan.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ShowDTO {
    private Movie movie;
    private Theatre theatre;
    private Screen screen;
    private Date date;
    private List<ShowSeat> seats;
    private List<ShowSeatType> showSeatTypes;
}
