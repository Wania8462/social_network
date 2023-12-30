package com.example.social_network.controller;

import com.example.social_network.dto.MediaPostDTO;
import com.example.social_network.dto.MediaUserDTO;
import com.example.social_network.model.Media;
import com.example.social_network.model.User;
import com.example.social_network.service.MediaService;
import com.example.social_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/media")
@RestController
public class MediaController {

    private final MediaService mediaService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/profileImage")
    public ResponseEntity<MediaUserDTO> uploadUserProfile(@RequestParam("file") MultipartFile file,
                                                          Principal principal) throws IOException {
        User user = userService.getUserByPrincipal(principal);
        Media response = mediaService.uploadMediaToUser(file, user.getId());
        return new ResponseEntity<>(
                mapProfile(response),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{post_id}/postImage")
    public ResponseEntity<MediaPostDTO> uploadPostImage(@RequestParam("file") MultipartFile file,
                                                            @PathVariable Long post_id,
                                                            Principal principal) throws IOException {
        Media response = mediaService.uploadMediaToPost(file, post_id, principal);
        return new ResponseEntity<>(
                mapPost(response),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{profile_id}/profileImage")
    public ResponseEntity<MediaUserDTO> getUserProfile(@PathVariable Long profile_id,
                                            Principal principal) {
        Media media = mediaService.getMediaUser(profile_id);
        return new ResponseEntity<>(
                mapProfile(media),
                HttpStatus.OK
        );
    }

    @GetMapping("/{post_id}/postImage")
    public ResponseEntity<List<MediaPostDTO>> getPostMedia(@PathVariable Long post_id,
                                            Principal principal) {
        List<MediaPostDTO> medias = mediaService.getMediaPost(post_id)
                .stream()
                .map(this::mapPost)
                .toList();

        return new ResponseEntity<>(
                medias,
                HttpStatus.OK
        );
    }

    private MediaUserDTO mapProfile(Media media) {
        return modelMapper.map(media, MediaUserDTO.class);
    }

    private MediaPostDTO mapPost(Media media) {
        return modelMapper.map(media, MediaPostDTO.class);
    }
}
