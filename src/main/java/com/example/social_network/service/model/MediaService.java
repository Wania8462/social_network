package com.example.social_network.service.model;

import com.example.social_network.exception.PostNotFoundException;
import com.example.social_network.model.Media;
import com.example.social_network.model.Post;
import com.example.social_network.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RequiredArgsConstructor
@Service
public class MediaService {
    private MediaRepository mediaRepository;
    private PostService postService;
    private UserService userService;

    public Media uploadImagePost(MultipartFile file, Long postId, Principal principal) throws IOException {
        Post post = postService.getAllForUser(principal)
                .stream()
                .filter(p -> p.getId().equals(postId))
                .findAny().orElseThrow(
                        () -> new PostNotFoundException("Post not found by id: " + postId));

        Media media = Media.builder()
                .post(post)
                .name(file.getName())
                .bytes(compressBytes(file.getBytes()))
                .build();

        return mediaRepository.save(media);
    }

    public Media getMediaPost(Long postId) {
        Media media = mediaRepository.findByPostId(postId).orElseThrow(
                () -> new RuntimeException("Make custom"));

        if(media != null) {
            media.setBytes(decompressBytes(media.getBytes()));
        }

        return media;
    }

    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.deflate(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return outputStream.toByteArray();
    }

    private byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }

        return outputStream.toByteArray();
    }
}