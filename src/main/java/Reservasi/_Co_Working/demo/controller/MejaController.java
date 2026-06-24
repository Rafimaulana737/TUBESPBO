package Reservasi._Co_Working.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.Meja;
import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.MejaService;
import Reservasi._Co_Working.demo.service.RoomService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/meja")
public class MejaController {

    private final MejaService mejaService;
    private final RoomService roomService;

    public MejaController(MejaService mejaService, RoomService roomService) {
        this.mejaService = mejaService;
        this.roomService = roomService;
    }

    @GetMapping("/page")
    public String mejaPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat mengelola meja");
            return "redirect:/";
        }

        Meja mejaForm = new Meja();
        mejaForm.setRoom(new Room());
        model.addAttribute("mejaList", mejaService.getAllMeja());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("mejaForm", mejaForm);
        return "meja";
    }

    @GetMapping("/page/edit/{id}")
    public String editMejaPage(@PathVariable Long id, HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat mengelola meja");
            return "redirect:/";
        }

        Meja meja = mejaService.getMejaById(id)
                .orElseThrow(() -> new RuntimeException("Meja tidak ditemukan"));
        model.addAttribute("mejaList", mejaService.getAllMeja());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("mejaForm", meja);
        model.addAttribute("editMode", true);
        return "meja";
    }

    @PostMapping("/page")
    public String createMeja(@ModelAttribute Meja mejaForm, HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat mengelola meja");
            return "redirect:/";
        }

        mejaService.saveMeja(mejaForm);
        redirectAttributes.addFlashAttribute("success", "Meja berhasil ditambahkan");
        return "redirect:/meja/page";
    }

    @PostMapping("/page/update/{id}")
    public String updateMeja(@PathVariable Long id, @ModelAttribute Meja mejaForm, HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat mengelola meja");
            return "redirect:/";
        }

        mejaService.updateMeja(id, mejaForm);
        redirectAttributes.addFlashAttribute("success", "Meja berhasil diperbarui");
        return "redirect:/meja/page";
    }

    @PostMapping("/page/delete/{id}")
    public String deleteMeja(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat mengelola meja");
            return "redirect:/";
        }

        mejaService.deleteMeja(id);
        redirectAttributes.addFlashAttribute("success", "Meja berhasil dihapus");
        return "redirect:/meja/page";
    }
}
