package com.example.social_network.Service;

import com.example.social_network.dto.PostDTO;
import com.example.social_network.model.Post;
import com.example.social_network.model.User;
import com.example.social_network.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Post create(PostDTO postDTO, Principal principal) {
        User user = getUserByPrincipal();
        Post post = Post.builder()
                .user(user)
                .text(postDTO.getText())
                .location(postDTO.getLocation())
                .build();

        return post;
    }

    private User getUserByPrincipal() {
        return null;
    }
}
