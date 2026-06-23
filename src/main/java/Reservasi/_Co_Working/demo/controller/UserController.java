package Reservasi._Co_Working.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // API JSON
    // =========================

    // GET ALL USER
    // URL: http://localhost:8080/user
    @GetMapping
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET USER BY ID
    // URL: http://localhost:8080/user/1
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    // CREATE USER
    // POST: http://localhost:8080/user
    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // DELETE USER
    // DELETE: http://localhost:8080/user/1
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // =========================
    // HALAMAN HTML THYMELEAF
    // =========================

    // URL: http://localhost:8080/user/page
    @GetMapping("/page")
    public String userPage(Model model) {

        // kirim data user ke html
        model.addAttribute("users", userService.getAllUsers());

        // menuju file:
        // src/main/resources/templates/user.html
        return "user";
    }
}