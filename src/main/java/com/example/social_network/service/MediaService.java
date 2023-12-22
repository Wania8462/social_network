package com.example.social_network.service;

import com.example.social_network.model.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface MediaService {
    Media uploadMediaToUser(MultipartFile file, Principal principal) throws IOException; // TODO: change principal to id
    Media uploadMediaToPost(MultipartFile file, Long postId, Principal principal) throws IOException;
    Media getMediaUser(Principal principal); // TODO: change principal to id
    Media getMediaPost(Long postId);
}
