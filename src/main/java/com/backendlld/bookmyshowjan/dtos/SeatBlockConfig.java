package com.backendlld.bookmyshowjan.dtos;

import java.util.Set;

public record SeatBlockConfig(int startRow,
                              int endRow,
                              int startCol,
                              int endCol,
                              String seatTypeName,
                              Set<String> holes) {
}
