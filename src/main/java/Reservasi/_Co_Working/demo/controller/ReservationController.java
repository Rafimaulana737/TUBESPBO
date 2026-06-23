package Reservasi._Co_Working.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(
          ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(
        @PathVariable Long id) {
        return reservationService
                .getReservationById(id)
                .orElse(null);
    }

    @PostMapping
    public  Reservation createReservation(
        @RequestBody Reservation reservation)  {
        return reservationService
                .saveReservation(reservation);
    } 

    @DeleteMapping("/{id}")
    public void deleteReservation(
        @PathVariable  Long id) {
        reservationService.deleteReservation(id);
    }
    @GetMapping("/page")
public String reservationPage(Model model) {
    model.addAttribute(
        "reservations",
        reservationService.getAllReservations()
    );

    return "reservation";
}
}
