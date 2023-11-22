package com.example.social_network.service.model;

import com.example.social_network.dto.PostDTO;
import com.example.social_network.exception.PostNotFoundException;
import com.example.social_network.model.Post;
import com.example.social_network.model.User;
import com.example.social_network.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final UserService userService;

    private final PostRepository postRepository;

    public Post create(PostDTO postDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        Post post = Post.builder()
                .user(user)
                .text(postDTO.getText())
                .location(postDTO.getLocation())
                .reactions(0)
                .createdDate(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    public List<Post> getAll() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public List<Post> getAllForUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return postRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found by id: " + id.toString())
        );
    }

    public Post like(Long id, Principal principal) {
        Post post = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userLikes = post.getLikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userLikes.isPresent())
            post.getLikeUsers().add(user.getUsername());

        else
            post.getLikeUsers().remove(user.getUsername());

        return postRepository.save(post); // Update?
    }

    public Post dislike(Long id, Principal principal) {
        Post post = getById(id);
        User user = userService.getUserByPrincipal(principal);

        Optional<String> userDislikes = post.getDislikeUsers()
                .stream().filter(u -> u.equals(user.getUsername())).findAny();

        if(!userDislikes.isPresent())
            post.getDislikeUsers().add(user.getUsername());

        else
            post.getDislikeUsers().remove(user.getUsername());

        return postRepository.save(post); // Update?
    }
}
