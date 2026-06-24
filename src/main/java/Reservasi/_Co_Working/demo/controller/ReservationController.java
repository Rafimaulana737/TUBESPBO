package Reservasi._Co_Working.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reservasi._Co_Working.demo.model.Meja;
import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.MejaService;
import Reservasi._Co_Working.demo.service.ReservationService;
import Reservasi._Co_Working.demo.service.RoomService;
import Reservasi._Co_Working.demo.service.UserService;
import Reservasi._Co_Working.demo.util.AuthHelper;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomService roomService;
    private final MejaService mejaService;

    public ReservationController(
            ReservationService reservationService,
            UserService userService,
            RoomService roomService,
            MejaService mejaService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.roomService = roomService;
        this.mejaService = mejaService;
    }

    @GetMapping("/cari")
    public String cariMejaPage(
            @RequestParam(required = false) LocalDate tanggal,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user) && !AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat membuat reservasi");
            return "redirect:/";
        }

        LocalDate searchDate = tanggal != null ? tanggal : LocalDate.now();
        List<Meja> availableMeja = reservationService.findAvailableMeja(searchDate);

        model.addAttribute("tanggal", searchDate);
        model.addAttribute("availableMeja", availableMeja);
        model.addAttribute("myReservations", reservationService.getReservationsByUser(user.getId()));
        return "cari-meja";
    }

    @PostMapping("/cari/book")
    public String bookMeja(
            @RequestParam LocalDate tanggal,
            @RequestParam Long mejaId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isMahasiswa(user)) {
            redirectAttributes.addFlashAttribute("error", "Hanya mahasiswa yang dapat membuat reservasi");
            return "redirect:/";
        }

        try {
            Reservation reservation = reservationService.createBooking(user, tanggal, mejaId);
            redirectAttributes.addFlashAttribute("success",
                    "Reservasi berhasil! Kode booking: " + reservation.getBookingKode());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/reservations/cari?tanggal=" + tanggal;
    }

    @GetMapping("/page")
    public String reservationsPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User user = AuthHelper.getCurrentUser(session);
        if (!AuthHelper.isAdmin(user)) {
            redirectAttributes.addFlashAttribute("error", "Halaman ini khusus admin");
            return "redirect:/reservations/cari";
        }

        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("mejaList", mejaService.getAllMeja());
        model.addAttribute("reservationForm", new Reservation());
        return "reservations";
    }

    @GetMapping("/page/edit/{id}")
    public String editReservationPage(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new RuntimeException("Reservasi tidak ditemukan"));
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("mejaList", mejaService.getAllMeja());
        model.addAttribute("reservationForm", reservation);
        model.addAttribute("editMode", true);
        return "reservations";
    }

    @PostMapping("/page")
    public String createReservationAdmin(
            @RequestParam LocalDate tanggal,
            @RequestParam Long userId,
            @RequestParam Long mejaId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        reservationService.createBooking(user, tanggal, mejaId);
        return "redirect:/reservations/page";
    }

    @PostMapping("/page/update/{id}")
    public String updateReservationAdmin(
            @PathVariable Long id,
            @RequestParam LocalDate tanggal,
            @RequestParam Long userId,
            @RequestParam Long mejaId,
            @RequestParam(required = false) String status) {
        Reservation reservation = new Reservation();
        reservation.setTanggal(tanggal);
        reservation.setUser(userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan")));
        Meja meja = mejaService.getMejaById(mejaId)
                .orElseThrow(() -> new RuntimeException("Meja tidak ditemukan"));
        reservation.setMeja(meja);
        reservation.setRoom(meja.getRoom());
        reservation.setStatus(status);
        reservationService.updateReservation(id, reservation);
        return "redirect:/reservations/page";
    }

    @PostMapping("/page/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations/page";
    }

    @GetMapping
    @ResponseBody
    public List<Reservation> getAllReservationsApi() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Reservation getReservationByIdApi(@PathVariable Long id) {
        return reservationService.getReservationById(id).orElse(null);
    }
}
