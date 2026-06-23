package Reservasi._Co_Working.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservasi tidak ditemukan"));

        reservation.setTanggal(reservationDetails.getTanggal());
        reservation.setUser(reservationDetails.getUser());
        reservation.setRoom(reservationDetails.getRoom());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
