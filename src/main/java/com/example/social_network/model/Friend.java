package com.example.social_network.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Friend {
    @EmbeddedId
    private Key key = new Key();

    @ManyToOne
    @Maps("ownerId")
    private User owner;

    @ManyToOne
    @MapsId("personId")
    private User person;

    @Embeddable
    public static class Key implements Serializable {
        private Long ownerId;
        private Long personId;
    }
}
