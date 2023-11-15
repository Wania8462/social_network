package com.example.social_network.repository;

import com.example.social_network.model.Contacts;
import com.example.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, User> {
}
