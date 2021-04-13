package com.example.jpajwtauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserCreationRequest {
    private String fullName;
    private String email;
    private String password;
}
