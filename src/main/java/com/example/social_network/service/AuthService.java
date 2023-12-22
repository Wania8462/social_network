package com.example.social_network.service;

import com.example.social_network.dto.request.AuthRequest;
import com.example.social_network.dto.request.RegisterRequest;
import com.example.social_network.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}
