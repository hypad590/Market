package com.hypad.Market.controller;

import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth/v1")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String login() {
        return "login";
    }
    @PostMapping("/authProceed")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {

        User user = User.builder()
                .name(username)
                .password(password)
                .email(email)
                .build();

        userRepository.saveUser(user);

        return "redirect:/products";
    }
    //todo JWT tokens
    @GetMapping("/api/getByEmail/{email}")
    @ResponseBody
    public User getByEmail(@PathVariable String email) {
        return userRepository.getUserByEmail(email);
    }

    @GetMapping("/api/getByName/{name}")
    @ResponseBody
    public User getByName(@PathVariable String name) {
        return userRepository.getUserByName(name);
    }

    @GetMapping("/api/getById/{id}")
    @ResponseBody
    public User getById(@PathVariable int id) {
        return userRepository.getUserById(id);
    }

    @GetMapping("/api/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
