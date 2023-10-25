package com.example.social_network.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
