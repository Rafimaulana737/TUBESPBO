package Reservasi._Co_Working.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email.trim().toLowerCase())
                .filter(user -> user.getPassword() != null && user.getPassword().equals(password));
    }
}
