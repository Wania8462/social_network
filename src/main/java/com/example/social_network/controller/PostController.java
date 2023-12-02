package com.example.social_network.controller;

import com.example.social_network.dto.PostDTO;
import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.model.Post;
import com.example.social_network.service.model.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("api/v1/post")
@RestController
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostRequest request, Principal principal) {
        Post post = postService.create(request, principal);
        return new ResponseEntity<>(
                map(post),
                HttpStatus.CREATED
        );
    }



    private PostDTO map(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }
}
