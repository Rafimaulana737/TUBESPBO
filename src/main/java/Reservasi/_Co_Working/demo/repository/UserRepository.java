package Reservasi._Co_Working.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Reservasi._Co_Working.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Kosong dulu tidak apa-apa, JpaRepository sudah otomatis menyediakan 
    // fungsi bawaan seperti save(), findAll(), findById(), dll.
}

