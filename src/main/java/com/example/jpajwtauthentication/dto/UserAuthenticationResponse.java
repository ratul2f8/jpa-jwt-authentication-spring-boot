package com.example.jpajwtauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAuthenticationResponse {
    private UUID uuid;
    private String email;
    private String fullName;
    private String jwt;
}
