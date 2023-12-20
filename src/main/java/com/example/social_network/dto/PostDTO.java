package com.example.social_network.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String text;
    private String location;
    private List<String> likeUsers;
    private List<String> dislikeUsers;
    private UserDTO user;
}
