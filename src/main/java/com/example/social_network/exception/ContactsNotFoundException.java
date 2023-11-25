package com.example.social_network.exception;

import com.example.social_network.model.Contacts;

public class ContactsNotFoundException extends RuntimeException {
    public ContactsNotFoundException(String message) {
        super(message);
    }
}
