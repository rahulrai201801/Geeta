package com.gitamentor.service;

import com.gitamentor.model.User;
import com.gitamentor.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> result = new HashMap<>();

        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Username cannot be empty.");
            return result;
        }

        if (password == null || password.length() < 4) {
            result.put("success", false);
            result.put("message", "Password must be at least 4 characters.");
            return result;
        }

        if (userRepository.existsByUsername(username.trim())) {
            result.put("success", false);
            result.put("message", "Username already taken. Please choose another.");
            return result;
        }

        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        result.put("success", true);
        result.put("message", "Registration successful! Welcome, " + username + ".");
        return result;
    }

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();

        Optional<User> optUser = userRepository.findByUsername(username.trim());

        if (optUser.isEmpty()) {
            result.put("success", false);
            result.put("message", "User not found. Please register first.");
            return result;
        }

        User user = optUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            result.put("success", false);
            result.put("message", "Incorrect password. Please try again.");
            return result;
        }

        result.put("success", true);
        result.put("message", "Login successful! Jai Shri Krishna 🙏");
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        return result;
    }
}
