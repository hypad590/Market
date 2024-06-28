package com.hypad.Market.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManagerService {
    public void saveCookies(String token, HttpServletResponse response){
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
