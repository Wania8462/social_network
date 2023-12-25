package com.example.social_network.controller;

import com.example.social_network.dto.MediaUserDTO;
import com.example.social_network.model.Media;
import com.example.social_network.model.User;
import com.example.social_network.service.MediaService;
import com.example.social_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("api/v1/media")
@RestController
public class MediaController {

    private final MediaService mediaService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadUserProfile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        User user = userService.getUserByPrincipal(principal);
        Media response = mediaService.uploadMediaToUser(file, user.getId());
        return new ResponseEntity<>(
                map(response),
                HttpStatus.CREATED
        );
    }

    private MediaUserDTO map(Media media) {
        return modelMapper.map(media, MediaUserDTO.class);
    }
}
