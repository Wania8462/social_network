package com.example.social_network.service;

import com.example.social_network.model.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface MediaService {
    Media uploadMediaToUser(MultipartFile file, Long userId) throws IOException;
    Media uploadMediaToPost(MultipartFile file, Long postId, Principal principal) throws IOException;
    Media getMediaUser(Long userId); // TODO: change principal to id
    Media getMediaPost(Long postId);
}
