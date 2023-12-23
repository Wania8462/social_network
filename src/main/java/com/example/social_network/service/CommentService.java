package com.example.social_network.service;

import com.example.social_network.dto.CommentDTO;
import com.example.social_network.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    Comment add(Long postId, CommentDTO commentDTO, Principal principal);
    Comment getById(Long id);
    List<Comment> getAllForPost(Long postId);
    Comment like(Long id, Principal principal);
    Comment dislike(Long id, Principal principal);
    void delete(Long id);
}