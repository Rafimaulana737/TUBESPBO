package Reservasi._Co_Working.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.UserRepository;

@Service
public class UserService {
     private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim().toLowerCase());
        }
        return userRepository.save(user);
    }

    public User register(String nama, String email, String password, String role) {
        if (userRepository.findByEmail(email.trim().toLowerCase()).isPresent()) {
            throw new RuntimeException("Email sudah terdaftar");
        }

        String normalizedRole = "mahasiswa";
        if (role != null && role.equalsIgnoreCase("petugas")) {
            normalizedRole = "petugas";
        }

        User user = new User();
        user.setNama(nama.trim());
        user.setEmail(email.trim().toLowerCase());
        user.setPassword(password);
        user.setRole(normalizedRole);
        return userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase()).isPresent();
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        user.setNama(userDetails.getNama());
        user.setEmail(userDetails.getEmail() != null ? userDetails.getEmail().trim().toLowerCase() : null);
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}