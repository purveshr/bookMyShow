package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.ShowSeat;
import com.backendlld.bookmyshowjan.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, String> {
    Optional<ShowSeatType>
    findByShowIdAndSeatTypeNameIgnoreCase(String showId, String seatTypeName);
}
