package com.example.springteamwork.service;
import com.example.springteamwork.repository.UserRepository;
import com.example.springteamwork.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    /*GET USER BY ID*/
    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        return optionalUser.get();
    }

    /*GET ALL USERS*/
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    /*LOGIN FORM*/
    @Override
    public void connectUser(String username, String password, HttpServletResponse response) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        else if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        Long userId = 0L;

        Optional<User> user = getAllUsers().stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();

        if (user.isPresent()){
            if(user.get().getPassword().equalsIgnoreCase(password)){
                userId = user.get().getId();
                user.get().setOnline(true);
                user.get().setNumber_of_visits(user.get().getNumber_of_visits()+1);
                userRepository.save(user.get());
            }
        }

        if (userId == 0){
            throw new IllegalArgumentException("Wrong Username or Password");
        } else {
            cookieGenerator(user.get(), response);
        }
    }

    /*LOGOUT FORM*/
    @Override
    public void disconnectUser(Long id, HttpServletResponse response) {
        cookieDeletor(response);
        User user = getUserById(id);
        user.setOnline(false);
        user.setToken(null);
        userRepository.save(user);
    }



    /*REGISTER FORM*/
    @Override
    public void saveUser(User user, boolean acceptTerms, HttpServletResponse response) {
        validateUser(user, acceptTerms, true, true, false);
        userRepository.save(user);
        cookieGenerator(user, response);
    }

    /*REGISTER FORM VALIDATOR*/
    private void validateUser(User user, boolean acceptTerms, boolean usernameChange, boolean emailChange, boolean isPhotoOptional) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        else if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        else if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        else if (usernameChange && getAllUsers().stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))) {
            throw new IllegalArgumentException("Username already used please choose another one");
        }
        else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        else if (emailChange && getAllUsers().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))) {
            throw new IllegalArgumentException("Email already used please choose another one");
        }
        else if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email must respect the good format: name@email.com");
        }
        else if (user.getPassword() == null || user.getPassword().isEmpty() || user.getRetypePassword() == null || user.getRetypePassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        else if ( !user.getPassword().equals(user.getRetypePassword()) ) {
            throw new IllegalArgumentException("Password must match");
        }
        else if (user.getStreet() == null) {
            user.setStreet("");
        }
        else if (user.getHouseNr() == null) {
            user.setHouseNr("");
        }
        else if (user.getCity() == null) {
            user.setCity("");
        }
        else if (user.getZip() == null) {
            user.setZip("");
        }
        else if (!acceptTerms) {
            throw new IllegalArgumentException("You must accept our terms");
        }
        else if ((user.getFile() == null || user.getFile().isEmpty()) && !isPhotoOptional) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        try {
            if (user.getFile() != null && !user.getFile().isEmpty() ) {
                user.setImage(user.getFile().getBytes());
            }
        } catch (IOException e){
            throw new IllegalArgumentException("Image cannot be uploaded");
        }

    }

    /*REGISTER FORM EMAIL VALIDATOR*/
    private boolean isValidEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    /*UPDATE USER*/
    @Override
    public void updateUser(User user) {
        validateUser(user, true, false, false, true);
        userRepository.save(user);
    }




    /*DELETE USER*/
    @Override
    public void deleteUser(Long id, HttpServletResponse response) {
        userRepository.deleteById(id);
    }




    /*COOKIE GENERATOR*/
    private void cookieGenerator(User user, HttpServletResponse response) {

        //CREATE COOKIE USER ID
        Cookie userCookie = new Cookie("userId", user.getId().toString());
        userCookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(userCookie);


        //CREATE COOKIE TOKEN
        String token = generateTheMostExtremlySecuredTokenOnEarth();
        user.setToken(token);
        userRepository.save(user);

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(tokenCookie);
    }

    /*COOKIE DELETOR*/
    private void cookieDeletor(HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Cookie cookieToken = new Cookie("token", "");
        cookieToken.setMaxAge(0);
        cookieToken.setPath("/");
        response.addCookie(cookieToken);
    }

    /*TOKEN GENERATOR*/
    private String generateTheMostExtremlySecuredTokenOnEarth() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            long timestamp = Instant.now().toEpochMilli();
            byte[] randomBytes = new byte[16];
            secureRandom.nextBytes(randomBytes);
            String token = Long.toHexString(timestamp) + Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
            return token;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } return null;
    }



}