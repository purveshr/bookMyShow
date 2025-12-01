package com.backendlld.bookmyshowjan.services;

import com.backendlld.bookmyshowjan.models.User;
import com.backendlld.bookmyshowjan.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordResetService(UserRepository userRepository,
                                EmailService emailService,
                                org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void requestOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf(100000 + new java.util.Random().nextInt(900000));
        user.setOtpCode(otp);
        user.setOtpExpiry(java.time.LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);

        emailService.sendOtpEmail(user.getEmail(), otp);
    }

    public void resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtpCode() == null || user.getOtpExpiry() == null) {
            throw new RuntimeException("OTP not requested");
        }

        if (!user.getOtpCode().equals(otp)) {
            throw new RuntimeException("Invalid OTP");
        }

        if (java.time.LocalDateTime.now().isAfter(user.getOtpExpiry())) {
            throw new RuntimeException("OTP expired");
        }

        String encoded = passwordEncoder.encode(newPassword);
        user.setPassword(encoded);
        user.setOtpCode(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
    }

}
