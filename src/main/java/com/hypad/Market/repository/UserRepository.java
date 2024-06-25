package com.hypad.Market.repository;

import com.hypad.Market.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByName(String name);
    User getUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
}
