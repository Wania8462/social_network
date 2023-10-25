package com.example.social_network.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(length = 3000)
    private String password;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToOne(mappedBy = "user")
    private Contacts contacts;

    @OneToMany( mappedBy="owner", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        createDate = LocalDateTime.now();
    }
}