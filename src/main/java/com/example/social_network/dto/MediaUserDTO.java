package com.example.social_network.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaUserDTO {
    private Long id;
    private String name;
    private byte[] bytes;
    private UserDTO user;
}
