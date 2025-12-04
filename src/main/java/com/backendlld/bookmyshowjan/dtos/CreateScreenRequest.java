package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateScreenRequest {
    private int totalCapacity;
    private List<SeatBlockConfig> seatBlocks = new ArrayList<>();

}
