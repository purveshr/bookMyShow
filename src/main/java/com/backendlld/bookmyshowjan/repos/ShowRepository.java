package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Date;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    boolean existsByScreenIdAndDateAndTime(String screenId, Date date, Time time);
}
