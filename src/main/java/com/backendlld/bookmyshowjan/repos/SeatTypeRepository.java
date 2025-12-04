package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatTypeRepository extends JpaRepository<SeatType, String> {
    Optional<SeatType> findByNameIgnoreCase(String name);


}
