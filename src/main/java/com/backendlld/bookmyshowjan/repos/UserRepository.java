package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Booking;
import com.backendlld.bookmyshowjan.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByMobileNumber(String mobileNumber);

    @NotNull
    Optional<User> findById(Integer id);
}
