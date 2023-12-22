package com.example.social_network.dto.request;

import lombok.Data;

@Data
public class PostRequest {
    private String text;
    private String location;
}
