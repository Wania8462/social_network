package com.example.social_network.dto;

import com.example.social_network.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String text;
    private String location;
    private Set<String> likedUser;
    private Set<String> dislikedUser;
    private UserDTO user;
}
