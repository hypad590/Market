package com.hypad.Market.repository;

import com.hypad.Market.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByEmail(String email);
    User getUserByName(String name);
    User getUserById(Long id);
    List<User> getAllUsers();
    void saveUser(User user);
    String getUserNameByToken(String token);
    User getUserByToken(String token);
}
