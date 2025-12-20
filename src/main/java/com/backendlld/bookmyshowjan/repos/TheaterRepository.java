package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    @Override
    Optional<Theater> findById(Integer id);
    Boolean existsByNameAndAddressAndCity(String name, String address, String city);

}
