package com.hypad.Market.controller;

import com.hypad.Market.Service.CookieManagerService;
import com.hypad.Market.controller.regAndAuth.AuthRequest;
import com.hypad.Market.model.User;
import com.hypad.Market.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/auth/v1")
public class AuthController {

    private final UserRepository userRepository;
    private boolean tokenFound = false;
    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String login(HttpServletRequest servletRequest) {
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())) {
                    tokenFound = true;

                    System.out.println(cookie.getValue());

                    User rememberedUser = userRepository.getUserByToken(cookie.getValue());

                    if(servletRequest.getSession().getAttribute("user") == null){
                        servletRequest.getSession().setAttribute("user",rememberedUser);
                    }
                    else{
                        break;
                    }
                }
            }
        }
        if(tokenFound){
            return "redirect:/products";
        }
        else{
            return "login";
        }
    }
    @PostMapping("/authProceed")
    public String login(@ModelAttribute AuthRequest request,
                        HttpServletResponse response,
                        CookieManagerService cookieManagerService) {
        if(!tokenFound){
            String token = UUID.randomUUID().toString();

            User user = User.builder()
                    .name(request.getName())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .token(token)
                    .build();
            try{
                userRepository.saveUser(user);
                cookieManagerService.saveCookies(token,response);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return "redirect:/products";
    }
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
    public User getById(@PathVariable Long id) {
        return userRepository.getUserById(id);
    }

    @GetMapping("/api/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
