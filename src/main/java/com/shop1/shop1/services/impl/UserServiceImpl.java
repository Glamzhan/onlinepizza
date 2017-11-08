package com.shop1.shop1.services.impl;

import com.shop1.shop1.entities.User;
import com.shop1.shop1.repositories.UserRepository;
import com.shop1.shop1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user.getName() == null){
            throw new IllegalArgumentException("Нет значения имени");
        }
        if (user.getName().isEmpty()){
            throw new IllegalArgumentException("Введите имя");
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

}
