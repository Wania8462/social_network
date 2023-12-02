package com.example.social_network.dto;

import com.example.social_network.model.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String text;
    private String location;
    private List<String> likeUser;
    private List<String> dislikeUser;
    private UserDTO user;
}
