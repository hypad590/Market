package com.hypad.Market.configuration.filter;

import com.hypad.Market.Service.inProgressUserService;
import com.hypad.Market.model.User;
import com.hypad.Market.proxy.ProxyUserRepository;
import com.hypad.Market.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class RememberMeFilter implements Filter {

    private final inProgressUserService userService;

    public RememberMeFilter(ProxyUserRepository proxyUserRepository) {
        this.userService = new inProgressUserService(proxyUserRepository);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            User rememberedUser = getRememberedUser(request);
            if(rememberedUser != null){
                request.getSession().setAttribute("user", rememberedUser);
            }
        }
        filterChain.doFilter(request,response);
    }

    private User getRememberedUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    return userService.getUserByToken(cookie.getValue());
                }
            }
        }
        return null;
    }
}
