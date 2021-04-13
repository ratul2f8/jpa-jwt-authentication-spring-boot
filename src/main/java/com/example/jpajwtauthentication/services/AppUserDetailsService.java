package com.example.jpajwtauthentication.services;

import com.example.jpajwtauthentication.dto.UserCreationRequest;
import com.example.jpajwtauthentication.entities.AppUser;
import com.example.jpajwtauthentication.entities.AppUserRole;
import com.example.jpajwtauthentication.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        String USER_NOT_FOUND_ERROR = "user with email: %s not found!";
        return appUserRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR, email)));
    }
    @Transactional
    public String createUser(UserCreationRequest request){
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        appUserRepository.save(new AppUser(request.getFullName(),
                request.getEmail(),
                encodedPassword,
                AppUserRole.USER));
        System.out.println("Added!");
        return "added";
    }
}
