package com.example.social_network.repository;

import com.example.social_network.model.Post;
import com.example.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedDateDesc();
    List<Post> findAllByUserOrderByCreatedDateDesc(User user);
}
