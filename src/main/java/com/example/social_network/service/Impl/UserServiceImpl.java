package com.example.social_network.service.Impl;

import com.example.social_network.model.User;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found by username: " + username)
        );
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found by id: " + id)
        );
    }
}