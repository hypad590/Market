package com.hypad.Market.controller;

import com.hypad.Market.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/v1")
public class AuthController {
    @GetMapping
    public String login() {
        return "login";
    }
    @PostMapping("/authProceed")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setEmail(email);

        return "redirect:/products";
    }
}
