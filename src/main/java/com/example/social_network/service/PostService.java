package com.example.social_network.service;

import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.model.Post;

import java.security.Principal;
import java.util.List;

public interface PostService {
    Post create(PostRequest postRequest, Principal principal);
    List<Post> getAll();
    List<Post> getAllForUser(Principal principal);
    Post getById(Long id);
    Post getById(Long id, Principal principal);
    Post like(Long id, Principal principal);
    Post dislike(Long id, Principal principal);
    void delete(Long id, Principal principal);
}
