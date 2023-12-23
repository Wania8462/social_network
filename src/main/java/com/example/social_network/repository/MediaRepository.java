package com.example.social_network.repository;

import com.example.social_network.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByPostId(Long postId);
    Optional<Media> findByUserId(Long userId);
}
