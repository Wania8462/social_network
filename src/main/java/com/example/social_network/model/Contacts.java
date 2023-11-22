package com.example.social_network.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Contacts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "contacts")
    private User user;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    public Contacts(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Column(nullable = false)
    private String email;
    private String phone;
}