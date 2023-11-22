package com.example.social_network.service.model;

import com.example.social_network.model.User;
import com.example.social_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found by username: " + username)
        );
    }
}
