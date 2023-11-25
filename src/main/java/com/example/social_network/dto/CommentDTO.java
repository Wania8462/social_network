package com.example.social_network.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String text;
    private String username;
}
