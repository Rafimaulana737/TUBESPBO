package Reservasi._Co_Working.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.ReservationService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    private final ReservationService reservationService;

    public CheckInController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/page")
    public String checkInPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isPetugas(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya petugas yang dapat validasi check-in/out");
            return "redirect:/";
        }

        model.addAttribute("reservations", reservationService.getAllReservations());
        return "checkin";
    }

    @PostMapping("/verify")
    public String verifyBooking(
            @RequestParam String bookingKode,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isPetugas(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya petugas yang dapat validasi check-in/out");
            return "redirect:/";
        }

        Reservation reservation = reservationService.getReservationByBookingKode(bookingKode).orElse(null);
        if (reservation == null) {
            redirectAttributes.addFlashAttribute("error", "ID booking tidak ditemukan");
            return "redirect:/checkin/page";
        }

        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("selectedReservation", reservation);
        return "checkin";
    }

    @PostMapping("/checkin")
    public String checkIn(@RequestParam String bookingKode, HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isPetugas(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya petugas yang dapat validasi check-in/out");
            return "redirect:/";
        }

        try {
            reservationService.checkIn(bookingKode);
            redirectAttributes.addFlashAttribute("success", "Check-in berhasil untuk booking " + bookingKode);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/checkin/page";
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam String bookingKode, HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isPetugas(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya petugas yang dapat validasi check-in/out");
            return "redirect:/";
        }

        try {
            reservationService.checkOut(bookingKode);
            redirectAttributes.addFlashAttribute("success", "Check-out berhasil untuk booking " + bookingKode);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/checkin/page";
    }
}
