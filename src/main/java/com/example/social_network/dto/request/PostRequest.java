package com.example.social_network.dto.request;

import com.example.social_network.service.model.PostService;
import lombok.Data;

@Data
public class PostRequest {
    private String text;
    private String location;
}
