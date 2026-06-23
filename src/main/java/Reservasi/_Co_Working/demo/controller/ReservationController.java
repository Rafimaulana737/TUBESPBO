package Reservasi._Co_Working.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.service.ReservationService;
import Reservasi._Co_Working.demo.service.RoomService;
import Reservasi._Co_Working.demo.service.UserService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomService roomService;

    public ReservationController(
            ReservationService reservationService,
            UserService userService,
            RoomService roomService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @GetMapping("/page")
    public String reservationsPage(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());
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
        model.addAttribute("reservationForm", reservation);
        model.addAttribute("editMode", true);
        return "reservations";
    }

    @PostMapping("/page")
    public String createReservation(
            @RequestParam LocalDate tanggal,
            @RequestParam Long userId,
            @RequestParam Long roomId) {
        Reservation reservation = new Reservation();
        reservation.setTanggal(tanggal);
        reservation.setUser(userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan")));
        reservation.setRoom(roomService.getRoomById(roomId)
                .orElseThrow(() -> new RuntimeException("Ruangan tidak ditemukan")));
        reservationService.saveReservation(reservation);
        return "redirect:/reservations/page";
    }

    @PostMapping("/page/update/{id}")
    public String updateReservation(
            @PathVariable Long id,
            @RequestParam LocalDate tanggal,
            @RequestParam Long userId,
            @RequestParam Long roomId) {
        Reservation reservation = new Reservation();
        reservation.setTanggal(tanggal);
        reservation.setUser(userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan")));
        reservation.setRoom(roomService.getRoomById(roomId)
                .orElseThrow(() -> new RuntimeException("Ruangan tidak ditemukan")));
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
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id).orElse(null);
    }

    @PostMapping
    @ResponseBody
    public Reservation createReservationApi(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Reservation updateReservationApi(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteReservationApi(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
