package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Seat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Seat s WHERE s.screen.id = :screenId")
    void deleteByScreenId(@Param("screenId") String screenId);
}
