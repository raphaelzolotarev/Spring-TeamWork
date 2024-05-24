package com.example.springteamwork.controller;

import com.example.springteamwork.model.User;
import com.example.springteamwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/new")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user"; // Assuming you have a Thymeleaf template named new_user.html for creating a new user
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String viewUserPage(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_details"; // Assuming you have a Thymeleaf template named user_details.html
    }


    @PostMapping("/users")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user"; // Assuming you have a Thymeleaf template named update_user.html for updating user details
    }
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
