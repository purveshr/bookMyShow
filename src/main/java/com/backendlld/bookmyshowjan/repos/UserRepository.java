package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Booking;
import com.backendlld.bookmyshowjan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByMobileNumber(String mobileNumber);
}
