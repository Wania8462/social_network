package com.example.social_network.controller;

import com.example.social_network.dto.PostDTO;
import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.model.Post;
import com.example.social_network.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll() {
        List<PostDTO> posts = postService.getAll()
                .stream()
                .map(this::map)
                .toList();

        return new ResponseEntity<>(
                posts,
                HttpStatus.OK
        );
    }

    @GetMapping("/for/user")
    public ResponseEntity<List<PostDTO>> getAllForUser(Principal principal) {
        List<PostDTO> posts = postService.getAllForUser(principal)
                .stream()
                .map(this::map)
                .toList();

        return new ResponseEntity<>(
                posts,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(
                map(postService.getById(id)),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<PostDTO> like(@PathVariable Long id, Principal principal) {
        Post post = postService.like(id, principal);
        return new ResponseEntity<>(
                map(post),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}/dislike")
    public ResponseEntity<PostDTO> dislike(@PathVariable Long id, Principal principal) {
        Post post = postService.dislike(id, principal);
        return new ResponseEntity<>(
                map(post),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostRequest request, Principal principal) {
        Post post = postService.create(request, principal);
        return new ResponseEntity<>(
                map(post),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal principal) {
        postService.delete(id, principal);
        return ResponseEntity.noContent().build();
    }

    private PostDTO map(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }
}
