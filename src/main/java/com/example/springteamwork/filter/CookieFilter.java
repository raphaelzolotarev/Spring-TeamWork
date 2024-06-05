package com.example.springteamwork.filter;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.FavoriteService;
import com.example.springteamwork.service.NumberOfVisitsService;
import com.example.springteamwork.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebFilter("/*")
public class CookieFilter implements Filter {

    private UserService userService;
    private NumberOfVisitsService numberOfVisitsService;
    private FavoriteService favoriteService;

    public CookieFilter(UserService userService, NumberOfVisitsService numberOfVisitsService, FavoriteService favoriteService) {
        this.userService = userService;
        this.numberOfVisitsService = numberOfVisitsService;
        this.favoriteService = favoriteService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //voor sidebar statistieken
        httpRequest.setAttribute("numberOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline).count());
        httpRequest.setAttribute("numberOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()).count());
        httpRequest.setAttribute("numberOfVisits", numberOfVisitsService.getNumberOfVisits().getNumberOfVisits());
        httpRequest.setAttribute("listOfConnectedUsers", userService.getAllUsers().stream().filter(User::isOnline));
        httpRequest.setAttribute("listOfDisconnectedUsers", userService.getAllUsers().stream().filter(user -> !user.isOnline()));
        httpRequest.setAttribute("myFavorites", new ArrayList<Favorite>());

        String cookieValue="";
        Cookie[] cookies = httpRequest.getCookies();

        httpRequest.setAttribute("userIdCookiePresent", false);
        httpRequest.setAttribute("cookieObject", new User());

        Long userId = 0L;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId") ) {
                    userId = Long.parseLong(cookie.getValue());
                }
                if (cookie.getName().equals("token") && cookie.getValue().equals(userService.getUserById(userId).getToken()) ) {
                    User user = userService.getUserById(userId);
                    final Long userIDFinal = userId;
                    httpRequest.setAttribute("userIdCookiePresent", true);
                    httpRequest.setAttribute("cookieObject", user);
                    httpRequest.setAttribute("myFavorites", favoriteService.getAllFavorites().stream().filter(f->f.getUser().getId()==userIDFinal).collect(Collectors.toList()));
                    break;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
