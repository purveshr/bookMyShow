package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    Optional<SeatType> findByNameIgnoreCase(String name);
}
