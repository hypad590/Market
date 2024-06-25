package com.hypad.Market.controller;

import com.hypad.Market.controller.auth.AuthenticationService;
import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    @GetMapping
    public String login() {
        return "login";
    }
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
/*    @PostMapping("/authProceed")
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
    }*/
    //todo JWT tokens
    @GetMapping("/api/getByEmail/{email}")
    @ResponseBody
    public Optional<User> getByEmail(@PathVariable String email) {
        return userRepository.getUserByEmail(email);
    }

    @GetMapping("/api/getByName/{name}")
    @ResponseBody
    public Optional<User> getByName(@PathVariable String name) {
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
