package com.example.springteamwork.service;
import com.example.springteamwork.repository.UserRepository;
import com.example.springteamwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        return (User) optionalUser.get();
    }

    @Override
    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);

    }
}