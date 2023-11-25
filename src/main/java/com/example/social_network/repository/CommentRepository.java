package com.example.social_network.repository;

import com.example.social_network.model.Comment;
import com.example.social_network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
