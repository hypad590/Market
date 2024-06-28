package com.hypad.Market.Service;

import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class inProgressUserService {
    private final UserRepository userRepository;

    public inProgressUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByToken(String token) {
      return userRepository.getUserByToken(token);
    }
}
