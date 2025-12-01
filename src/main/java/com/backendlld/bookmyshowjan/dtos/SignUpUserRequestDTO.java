package com.backendlld.bookmyshowjan.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDTO {
    private String username;
    private String mobileNumber;
    private String email;
    private String password;
}
