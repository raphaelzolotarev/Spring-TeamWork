package com.example.springteamwork.filter;
import com.example.springteamwork.dto.Role;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/*")
public class CookieFilter implements Filter {
    private UserService userService;
    public CookieFilter(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String cookieValue="";
        Cookie[] cookies = httpRequest.getCookies();
        boolean hasCookie = true;
<<<<<<< HEAD
        httpRequest.setAttribute("numberOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline).count());
        httpRequest.setAttribute("numberOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()).count());
        httpRequest.setAttribute("numberOfVisits", userService.getAllUsers().stream().mapToLong(User::getNumber_of_visits).sum());
        httpRequest.setAttribute("listOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline));
        httpRequest.setAttribute("listOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()));
=======

        httpRequest.setAttribute("numberOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline).count());
        httpRequest.setAttribute("numberOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()).count());
        httpRequest.setAttribute("numberOfVisits", userService.getAllUsers().stream().mapToLong(User::getNumber_of_visits).sum());

        httpRequest.setAttribute("listOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline));
        httpRequest.setAttribute("listOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()));

>>>>>>> origin/master
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    cookieValue= cookie.getValue();
                    httpRequest.setAttribute("userIdCookiePresent", true);
                    httpRequest.setAttribute("cookieObject", userService.getUserById(Long.parseLong(cookieValue)));
                    hasCookie=true;
                    break;
                }else {
                    hasCookie=false;
                }
            }
        }
        if (!hasCookie){
            httpRequest.setAttribute("userIdCookiePresent", false);
            httpRequest.setAttribute("cookieObject", new User());
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }

}