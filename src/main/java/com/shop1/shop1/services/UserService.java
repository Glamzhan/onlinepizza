package com.shop1.shop1.services;

import com.shop1.shop1.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> findAll();

    User getUserById(Long id);

    User getUserByName(String name);
}
