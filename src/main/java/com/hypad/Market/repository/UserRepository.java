package com.hypad.Market.repository;

import com.hypad.Market.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByEmail(String email);
    User getUserByName(String name);
    User getUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
}
