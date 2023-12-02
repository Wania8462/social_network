package com.example.social_network.dto;

import com.example.social_network.model.Post;
import com.example.social_network.model.enums.ERole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private LocalDateTime createDate;
    private Set<ERole> roles;
    private List<Post> posts;
}
