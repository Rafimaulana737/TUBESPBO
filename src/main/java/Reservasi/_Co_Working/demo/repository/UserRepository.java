package Reservasi._Co_Working.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Reservasi._Co_Working.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
