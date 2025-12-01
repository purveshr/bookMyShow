package com.backendlld.bookmyshowjan;

import com.backendlld.bookmyshowjan.controllers.UserController;
import com.backendlld.bookmyshowjan.dtos.SignUpUserRequestDTO;
import com.backendlld.bookmyshowjan.dtos.SignUpUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowJan24BeginnerApplication{
    public static void main(String[] args) {
        SpringApplication.run(BookMyShowJan24BeginnerApplication.class, args);
    }
}
