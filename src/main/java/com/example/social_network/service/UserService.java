package com.example.social_network.service;

import com.example.social_network.model.User;

import java.security.Principal;

public interface UserService {
    User getUserByPrincipal(Principal principal);
    User getUserById(Long id);
}
