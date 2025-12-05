package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {
}
