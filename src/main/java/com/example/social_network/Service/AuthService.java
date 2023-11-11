package com.example.social_network.Service;

import com.example.social_network.dto.request.AuthRequest;
import com.example.social_network.dto.request.RegisterRequest;
import com.example.social_network.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    public AuthResponse register(RegisterRequest request) {
        return null;
    }

    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }
}
