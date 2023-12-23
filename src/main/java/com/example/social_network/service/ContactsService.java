package com.example.social_network.service;

import com.example.social_network.dto.ContactsDTO;
import com.example.social_network.model.Contacts;

import java.security.Principal;

public interface ContactsService {
//     Contacts create(Principal principal, String firstname, String lastname, String email, String phone);
    Contacts getByUserId(Principal principal);
    Contacts update(ContactsDTO contactsDTO, Principal principal);
}
