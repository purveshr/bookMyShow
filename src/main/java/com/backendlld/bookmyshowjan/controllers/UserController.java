package com.backendlld.bookmyshowjan.controllers;

import com.backendlld.bookmyshowjan.dtos.*;
import com.backendlld.bookmyshowjan.dtos.ResponseStatus;
import com.backendlld.bookmyshowjan.models.User;
import com.backendlld.bookmyshowjan.models.UserRole;
import com.backendlld.bookmyshowjan.repos.UserRepository;
import com.backendlld.bookmyshowjan.services.PasswordResetService;
import com.backendlld.bookmyshowjan.services.ShowSeatLayoutService;
import com.backendlld.bookmyshowjan.services.UserService;
import com.backendlld.bookmyshowjan.utilities.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ShowSeatLayoutService showSeatLayoutService;
    private UserRepository userRepository;
    private PasswordResetService passwordResetService;

    @Autowired
    public UserController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          ShowSeatLayoutService showSeatLayoutService,
                          UserRepository userRepository,
                          PasswordResetService passwordResetService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.showSeatLayoutService = showSeatLayoutService;
    }

    @GetMapping("greet")
    public String greet(Authentication authentication) {
        return "hello " + authentication.getName();
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDTO> signUpUser(@RequestBody SignUpUserRequestDTO request){
        SignUpUserResponseDTO response = new SignUpUserResponseDTO();
        try{
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            User user = userService.signUpUser(request.getUsername(), request.getEmail(),encodedPassword, request.getMobileNumber());
            response.setUserId(user.getId());
            response.setMessage("User successfully registered!");
            response.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage("Failed to signup!" +  e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.getUsername(), request.getPassword())
        );

        // if we reach here, authentication succeeded
        String token = jwtUtil.generateToken(request.getUsername());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setMessage("Login successful");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/requestOtp")
    public ResponseEntity<String> requestOtp(@RequestBody RequestOtpDTO request) {
        try {
            passwordResetService.requestOtp(request.getEmail());
            return ResponseEntity.ok("OTP sent to your email");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO request) {
        try {
            passwordResetService.resetPassword(
                    request.getEmail(),
                    request.getOtp(),
                    request.getNewPassword()
            );
            return ResponseEntity.ok("Password reset successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to reset password: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PutMapping("/{userId}/role")
    @PreAuthorize("hasRole('ADMIN')")  // Only admins can update roles
    public ResponseEntity<?> updateUserRole(@PathVariable String userId,
                                            @RequestBody Map<String, String> roleRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String roleStr = roleRequest.get("role");   // read "role" from JSON body
        if (roleStr == null) {
            return ResponseEntity.badRequest().body("role is required");
        }
        try {
            UserRole role = UserRole.valueOf(roleStr.toUpperCase()); // e.g. "ADMIN"
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role: " + roleStr);
        }

        userRepository.save(user);
        return ResponseEntity.ok("Role updated");
    }

    @GetMapping("/{showId}/seats")
    public ResponseEntity<List<ShowSeatResponseDTO>> getShowSeats(@PathVariable Integer showId) {
        return ResponseEntity.ok(showSeatLayoutService.getSeatsForShow(showId));
    }
}



