package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Integer> {
    Optional<ShowSeatType>
    findByShowIdAndSeatTypeNameIgnoreCase(Integer showId, String seatTypeName);
}
