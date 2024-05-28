package com.example.springteamwork.service;
import com.example.springteamwork.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user, boolean acceptTerms, HttpServletResponse response);
    User getUserById(Long id);
    void connectUser(String username, String password, HttpServletResponse response);
    void disconnectUser(Long id, HttpServletResponse response);
    void updateUser(User user);
    void deleteUser(Long id, HttpServletResponse response);
}
