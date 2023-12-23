package com.example.social_network.service.Impl;

import com.example.social_network.dto.ContactsDTO;
import com.example.social_network.model.Contacts;
import com.example.social_network.model.User;
import com.example.social_network.repository.ContactsRepository;
import com.example.social_network.service.ContactsService;
import com.example.social_network.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsRepository contactsRepository;
    private final UserService userService;

    public Contacts getByUserId(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return user.getContacts();
    }

    public Contacts update(ContactsDTO contactsDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Contacts contacts = user.getContacts();
        contacts.setFirstname(contactsDTO.getFirstname());
        contacts.setLastname(contactsDTO.getLastname());
        contacts.setEmail(contactsDTO.getEmail());
        contacts.setPhone(contactsDTO.getPhone());

        return contactsRepository.save(contacts);
    }
}
