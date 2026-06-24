package Reservasi._Co_Working.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.AuthService;
import Reservasi._Co_Working.demo.service.UserService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (AuthHelper.getCurrentUser(session) != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = authService.login(email.trim().toLowerCase(), password).orElse(null);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Email atau password salah");
            return "redirect:/login";
        }

        session.setAttribute(AuthHelper.SESSION_USER, user);
        redirectAttributes.addFlashAttribute("success", "Selamat datang, " + user.getNama() + "!");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session) {
        if (AuthHelper.getCurrentUser(session) != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String nama,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(defaultValue = "mahasiswa") String role,
            RedirectAttributes redirectAttributes) {
        if (nama == null || nama.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Nama wajib diisi");
            return "redirect:/register";
        }
        if (password == null || password.length() < 4) {
            redirectAttributes.addFlashAttribute("error", "Password minimal 4 karakter");
            return "redirect:/register";
        }
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Konfirmasi password tidak cocok");
            return "redirect:/register";
        }

        try {
            userService.register(nama, email, password, role);
            redirectAttributes.addFlashAttribute("success", "Akun berhasil dibuat! Silakan login.");
            return "redirect:/login";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
