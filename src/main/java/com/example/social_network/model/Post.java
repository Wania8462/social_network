package com.example.social_network.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String text;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    private String location;

    private Integer likeAmount;
    private Integer dislikeAmount;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> likeUsers = new ArrayList<>();

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> dislikeUsers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Media> mediaList;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
