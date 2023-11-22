package com.example.social_network.service;

import com.example.social_network.dto.request.AuthRequest;
import com.example.social_network.dto.request.RegisterRequest;
import com.example.social_network.dto.response.AuthResponse;
import com.example.social_network.model.Contacts;
import com.example.social_network.model.User;
import com.example.social_network.model.enums.ERole;
import com.example.social_network.repository.ContactsRepository;
import com.example.social_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ContactsRepository contactsRepository;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .contacts(
                        contactsRepository.save(new Contacts(
                                request.getFirstname(),
                                request.getLastname(),
                                request.getEmail()
                        ))
                )
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(ERole.USER))
                .createDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

        String jwt = jwtService.generateToken(
                userDetailsService.loadUserByUsername(user.getUsername()));

        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found by username: " + request.getUsername())
                );

        String jwt = jwtService.generateToken(
                userDetailsService.loadUserByUsername(user.getUsername()));

        return new AuthResponse(jwt);
    }
}