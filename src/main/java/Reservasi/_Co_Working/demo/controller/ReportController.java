package Reservasi._Co_Working.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.ReportService;
import Reservasi._Co_Working.demo.service.ReservationService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final ReservationService reservationService;

    public ReportController(ReportService reportService, ReservationService reservationService) {
        this.reportService = reportService;
        this.reservationService = reservationService;
    }

    @GetMapping("/page")
    public String reportsPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat mengirim laporan");
            return "redirect:/";
        }

        if (AuthHelper.isAdmin(user)) {
            model.addAttribute("reports", reportService.getAllReports());
            model.addAttribute("adminView", true);
        } else {
            model.addAttribute("reports", reportService.getAllReports().stream()
                    .filter(r -> r.getUser().getId().equals(user.getId()))
                    .toList());
            model.addAttribute("myReservations", reservationService.getReservationsByUser(user.getId()));
        }
        return "reports";
    }

    @PostMapping("/page")
    public String submitReport(
            @RequestParam Long reservationId,
            @RequestParam String deskripsiKerusakan,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat mengirim laporan");
            return "redirect:/";
        }

        try {
            reportService.submitReport(user, reservationId, deskripsiKerusakan);
            redirectAttributes.addFlashAttribute("success", "Laporan kerusakan berhasil dikirim");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/reports/page";
    }

    @PostMapping("/page/status/{id}")
    public String updateReportStatus(
            @PathVariable Long id,
            @RequestParam String status,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya admin yang dapat memperbarui status laporan");
            return "redirect:/";
        }

        reportService.updateStatus(id, status);
        redirectAttributes.addFlashAttribute("success", "Status laporan diperbarui");
        return "redirect:/reports/page";
    }
}
