package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getUserByName(String name);
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
