package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel {
    private int userId;
    private String name;
    private String username;
    private String email;
    private String password;
    private String mobileNumber;
    private String otpCode;
    private java.time.LocalDateTime otpExpiry;
    @OneToMany
    private List<Booking> bookings;
}
// User  1  :  M   Bookings