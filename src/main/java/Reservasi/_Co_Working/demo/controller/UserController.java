package Reservasi._Co_Working.demo.controller;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.UserService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userForm", new User());
        return "users";
    }

    @GetMapping("/page/edit/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userForm", user);
        model.addAttribute("editMode", true);
        return "users";
    }

    @PostMapping("/page")
    public String createUser(@ModelAttribute User userForm) {
        userService.saveUser(userForm);
        return "redirect:/user/page";
    }

    @PostMapping("/page/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User userForm) {
        userService.updateUser(id, userForm);
        return "redirect:/user/page";
    }

    @PostMapping("/page/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user/page";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @PostMapping("/api")
    @ResponseBody
    public User createUserApi(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public User updateUserApi(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteUserApi(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
