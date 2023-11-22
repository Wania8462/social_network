package com.example.social_network.dto;

import com.example.social_network.model.User;
import lombok.Data;

@Data
public class PostDTO {
    private String text;
    private String location;
//    private User user;
}
