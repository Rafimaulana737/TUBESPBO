package Reservasi._Co_Working.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.FeedbackService;
import Reservasi._Co_Working.demo.service.ReservationService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ReservationService reservationService;

    public FeedbackController(FeedbackService feedbackService, ReservationService reservationService) {
        this.feedbackService = feedbackService;
        this.reservationService = reservationService;
    }

    @GetMapping("/page")
    public String feedbackPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat memberi feedback");
            return "redirect:/";
        }

        if (AuthHelper.isAdmin(user)) {
            model.addAttribute("feedbacks", feedbackService.getAllFeedback());
            model.addAttribute("adminView", true);
        } else {
            model.addAttribute("feedbacks", feedbackService.getAllFeedback().stream()
                    .filter(f -> f.getUser().getId().equals(user.getId()))
                    .toList());
            model.addAttribute("completedReservations", reservationService.getReservationsByUser(user.getId()).stream()
                    .filter(r -> "SELESAI".equalsIgnoreCase(r.getStatus()))
                    .toList());
        }
        return "feedback";
    }

    @PostMapping("/page")
    public String submitFeedback(
            @RequestParam Long reservationId,
            @RequestParam int rating,
            @RequestParam String komentar,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat memberi feedback");
            return "redirect:/";
        }

        try {
            feedbackService.submitFeedback(user, reservationId, rating, komentar);
            redirectAttributes.addFlashAttribute("success", "Feedback berhasil dikirim");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/feedback/page";
    }
}
