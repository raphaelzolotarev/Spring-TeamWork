package com.example.springteamwork.service;
import com.example.springteamwork.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getUserByUserName(String username);
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
