package Reservasi._Co_Working.demo.controller;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ==========================================
    // HALAMAN HTML THYMELEAF
    // ==========================================

    // URL: http://localhost:8080/user/page
    @GetMapping("/page")
    public String userPage(Model model) {
        // Kirim data user ke html
        model.addAttribute("users", userService.getAllUsers());
        
        // Menuju file: src/main/resources/templates/user.html (atau users.html)
        return "user";
    }

    // ==========================================
    // API JSON ENDPOINTS
    // ==========================================

    // GET ALL USERS
    // URL: http://localhost:8080/user/api
    @GetMapping("/api")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET USER BY ID
    // URL: http://localhost:8080/user/api/1
    @GetMapping("/api/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    // CREATE USER
    // URL: http://localhost:8080/user/api
    @PostMapping("/api")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // DELETE USER
    // URL: http://localhost:8080/user/api/1
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}