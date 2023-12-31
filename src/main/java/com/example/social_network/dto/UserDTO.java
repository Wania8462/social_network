package com.example.social_network.dto;

import com.example.social_network.model.enums.ERole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private LocalDateTime createDate;
    private List<ERole> roles;
}
