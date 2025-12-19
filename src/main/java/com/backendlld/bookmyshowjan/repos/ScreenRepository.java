package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

    @Override
    Optional<Screen> findById(Integer integer);
    Optional<Screen> findByNameAndTheaterId(String name,  Integer theaterId);
}
