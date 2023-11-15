package com.example.social_network.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Contacts {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;
    private String phone;

    public Contacts(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}