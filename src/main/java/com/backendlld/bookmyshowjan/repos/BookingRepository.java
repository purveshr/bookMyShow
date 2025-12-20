package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
