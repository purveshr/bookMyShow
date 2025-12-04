package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, String> {
    @Override
    Optional<Theater> findById(String id);

}
