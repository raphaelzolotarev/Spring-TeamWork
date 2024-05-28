package com.example.springteamwork.controller;

import com.example.springteamwork.model.User;
import com.example.springteamwork.service.UserServiceImpl;
import jakarta.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;



    /*LOGIN FORM*/
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String connectUser(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        try {
            userService.connectUser(username, password, response);
            return "redirect:/";
        } catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }



    /*LOGOUT*/
    @GetMapping("/logout/{id}")
    public String logout(@PathVariable(value="id") Long id, HttpServletResponse response) {
        userService.disconnectUser(id, response);
        return "redirect:/";
    }



    /*REGISTER FORM*/
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User newuser = new User();
        model.addAttribute("newuser", newuser);
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User newuser, HttpServletResponse response, Model model, @RequestParam(name = "acceptTerms", required = false) boolean acceptTerms) {
        try {
            userService.saveUser(newuser, acceptTerms, response);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("newuser", newuser);
            return "register";
        }
    }



    /*EDIT FORM*/
    @GetMapping("/edit")
    public String showEditForm() {
        return "edit-user-infos";
    }
    @PostMapping("/edit")
    public String editUser(
            @RequestParam("id") Long id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("retypePassword") String retypePassword,
            @RequestParam("street") String street,
            @RequestParam("housenbr") String housenbr,
            @RequestParam("city") String city,
            @RequestParam("zip") String zip,
            Model model) {
        try {
            User updatedUser = userService.getUserById(id);
            updatedUser.setFirstName(firstname);
            updatedUser.setLastName(lastname);
            updatedUser.setEmail(email);
            updatedUser.setPassword(password);
            updatedUser.setRetypePassword(retypePassword);
            updatedUser.setStreet(street);
            updatedUser.setHouseNr(housenbr);
            updatedUser.setCity(city);
            updatedUser.setZip(zip);
            userService.updateUser(updatedUser);
            return "redirect:/edit";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "edit-user-infos";
        }
    }



    /*DELETE USER*/
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value="id") Long id, HttpServletResponse response) {
        userService.deleteUser(id, response);
        return "redirect:/";
    }

}
