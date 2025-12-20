package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.models.User;
import com.backendlld.bookmyshowjan.models.UserRole;
import com.backendlld.bookmyshowjan.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public User signUpUser(String username, String email, String password, String mobileNumber){
        // Check if user is already registered using email
        Optional<User> userOptional =  userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException("user with email already exists, please reset the password");
        }
        // Check if userID is already registered.
        Optional<User> userIdPresent =  userRepository.findByUsername(username);
        if(userIdPresent.isPresent()){
            throw new RuntimeException("This User Id is already exists, please choose new one");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(username);
        user.setUsername(username);
        user.setMobileNumber(mobileNumber);
        user.setRole(UserRole.CUSTOMER); // Default role
        return userRepository.save(user);
    }

    public void generateAndSendOtp(String email, String mobileNumber) {
        User user = userRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf(100000 + new java.util.Random().nextInt(900000));
        user.setOtpCode(otp);
        user.setOtpExpiry(java.time.LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);

        // send OTP email
        emailService.sendOtpEmail(user.getEmail(), otp);
    }

    public String updateUserRole(Integer userId, String roleStr) {
        // 1. Validate input
        if (roleStr == null || roleStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }

        // 2. Validate and convert role string to enum
        UserRole role;
        try {
            role = UserRole.valueOf(roleStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleStr +
                    ". Valid roles: " + Arrays.toString(UserRole.values()));
        }

        // 3. Find and update user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setRole(role);  // Set as enum, not string
        userRepository.save(user);

        // 4. Return success message
        return "Role updated successfully to: " + role;
    }
}
