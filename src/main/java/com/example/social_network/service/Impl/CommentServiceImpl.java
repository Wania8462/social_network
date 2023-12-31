package com.example.social_network.service.Impl;

import com.example.social_network.dto.CommentDTO;
import com.example.social_network.exception.CommentNotFoundException;
import com.example.social_network.model.Comment;
import com.example.social_network.model.Post;
import com.example.social_network.model.User;
import com.example.social_network.repository.CommentRepository;
import com.example.social_network.service.CommentService;
import com.example.social_network.service.PostService;
import com.example.social_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    @Override
    public Comment add(Long postId, CommentDTO commentDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Post post = postService.getById(postId);

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .text(commentDTO.getText())
                .createDate(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new CommentNotFoundException("Comment not found by id: " + id)
        );
    }

    @Override
    public List<Comment> getAllForPost(Long postId) {
        Post post = postService.getById(postId);
        return commentRepository.findAllByPost(post);
    }

    @Override
    public Comment like(Long id, Principal principal) {
        Comment comment = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userLikes = comment.getLikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userLikes.isEmpty())
            comment.getLikeUsers().add(user.getUsername());

        else
            comment.getLikeUsers().remove(user.getUsername());

        return commentRepository.save(comment);
    }

    @Override
    public Comment dislike(Long id, Principal principal) {
        Comment comment = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userDislikes = comment.getDislikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userDislikes.isEmpty())
            comment.getDislikeUsers().add(user.getUsername());

        else
            comment.getDislikeUsers().remove(user.getUsername());

        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = getById(id);
        commentRepository.delete(comment);
    }
}