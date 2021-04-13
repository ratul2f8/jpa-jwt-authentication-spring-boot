package com.example.jpajwtauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAuthenticationRequest {
    private String email;
    private String password;
}
