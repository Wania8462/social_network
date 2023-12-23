package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.exception.PostNotFoundException;
import com.example.social_network.model.Post;
import com.example.social_network.model.User;
import com.example.social_network.repository.PostRepository;
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
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    @Override
    public Post create(PostRequest postRequest, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        Post post = Post.builder()
                .user(user)
                .text(postRequest.getText())
                .location(postRequest.getLocation())
                .createdDate(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public List<Post> getAllForUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return postRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found by id: " + id)
        );
    }

    @Override
    public Post getById(Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        return postRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new PostNotFoundException("Post not found by id: " + id)
        );
    }

    @Override
    public Post like(Long id, Principal principal) {
        Post post = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userLikes = post.getLikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userLikes.isPresent())
            post.getLikeUsers().add(user.getUsername());

        else
            post.getLikeUsers().remove(user.getUsername());

        return postRepository.save(post);
    }

    @Override
    public Post dislike(Long id, Principal principal) {
        Post post = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userDislikes = post.getDislikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userDislikes.isPresent())
            post.getDislikeUsers().add(user.getUsername());

        else
            post.getDislikeUsers().remove(user.getUsername());

        return postRepository.save(post);
    }

    @Override
    public void delete(Long id, Principal principal) {
        Post post = getById(id, principal);
        postRepository.delete(post);
    }
}
