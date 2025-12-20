package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    boolean existsByScreenIdAndDateAndTime(Integer screenId, Date date, Time time);

    @Query("SELECT s FROM Show s WHERE s.screen.id = :screenId AND s.date = :date")
    List<Show> findByScreenAndDate(@Param("screenId") Integer screenId,
                                   @Param("date") Date date);

    List<Show> findByTheater_IdAndDate(Integer theaterId, Date date);
}
