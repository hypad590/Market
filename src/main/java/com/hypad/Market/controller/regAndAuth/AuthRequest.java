package com.hypad.Market.controller.regAndAuth;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String name;
}
