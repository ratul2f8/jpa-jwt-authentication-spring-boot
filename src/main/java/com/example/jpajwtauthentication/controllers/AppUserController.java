package com.example.jpajwtauthentication.controllers;

import com.example.jpajwtauthentication.dto.UserAuthenticationRequest;
import com.example.jpajwtauthentication.dto.UserAuthenticationResponse;
import com.example.jpajwtauthentication.dto.UserCreationRequest;
import com.example.jpajwtauthentication.entities.AppUser;
import com.example.jpajwtauthentication.services.AppUserDetailsService;
import com.example.jpajwtauthentication.services.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class AppUserController {
    private final AppUserDetailsService appUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @PostMapping(path = "/create")
    public String createUser(@RequestBody UserCreationRequest request){
        return appUserDetailsService.createUser(request);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<UserAuthenticationResponse>
    authenticateUser(@RequestBody UserAuthenticationRequest request)
    throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        }catch (Exception e){
            System.out.println("**********Error********" + e);
            throw new Exception("Exception found! ",e);
        }
        final AppUser userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(
                new UserAuthenticationResponse(
                        userDetails.getUserId(),
                        userDetails.getUsername(),
                        userDetails.getFullName(),
                        jwt));
    }
}
