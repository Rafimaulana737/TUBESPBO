package Reservasi._Co_Working.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Meja;
import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.MejaRepository;
import Reservasi._Co_Working.demo.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MejaRepository mejaRepository;

    public ReservationService(ReservationRepository reservationRepository, MejaRepository mejaRepository) {
        this.reservationRepository = reservationRepository;
        this.mejaRepository = mejaRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Optional<Reservation> getReservationByBookingKode(String bookingKode) {
        if (bookingKode == null || bookingKode.isBlank()) {
            return Optional.empty();
        }
        return reservationRepository.findFirstByBookingKodeOrderByIdDesc(bookingKode.trim());
    }

    public List<Meja> findAvailableMeja(LocalDate tanggal) {
        List<Long> bookedMejaIds = reservationRepository.findBookedMejaIdsByTanggal(tanggal);
        return mejaRepository.findAll().stream()
                .filter(meja -> "tersedia".equalsIgnoreCase(meja.getStatus()))
                .filter(meja -> !bookedMejaIds.contains(meja.getId()))
                .toList();
    }

    public Reservation createBooking(User user, LocalDate tanggal, Long mejaId) {
        Meja meja = mejaRepository.findById(mejaId)
                .orElseThrow(() -> new RuntimeException("Meja tidak ditemukan"));

        if (!"tersedia".equalsIgnoreCase(meja.getStatus())) {
            throw new RuntimeException("Meja tidak tersedia");
        }

        List<String> inactiveStatuses = List.of("DIBATALKAN", "SELESAI");
        if (reservationRepository.existsByMejaIdAndTanggalAndStatusNotIn(mejaId, tanggal, inactiveStatuses)) {
            throw new RuntimeException("Meja sudah direservasi pada tanggal tersebut");
        }

        Reservation reservation = new Reservation();
        reservation.setBookingKode(generateBookingKode());
        reservation.setTanggal(tanggal);
        reservation.setStatus("MENUNGGU");
        reservation.setUser(user);
        reservation.setMeja(meja);
        reservation.setRoom(meja.getRoom());
        return reservationRepository.save(reservation);
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.getBookingKode() == null || reservation.getBookingKode().isBlank()) {
            reservation.setBookingKode(generateBookingKode());
        }
        if (reservation.getStatus() == null || reservation.getStatus().isBlank()) {
            reservation.setStatus("MENUNGGU");
        }
        if (reservation.getMeja() != null && reservation.getRoom() == null) {
            reservation.setRoom(reservation.getMeja().getRoom());
        }
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservasi tidak ditemukan"));

        reservation.setTanggal(reservationDetails.getTanggal());
        reservation.setUser(reservationDetails.getUser());
        reservation.setMeja(reservationDetails.getMeja());
        reservation.setRoom(reservationDetails.getRoom());
        if (reservationDetails.getStatus() != null) {
            reservation.setStatus(reservationDetails.getStatus());
        }
        return reservationRepository.save(reservation);
    }

    public Reservation checkIn(String bookingKode) {
        Reservation reservation = getReservationByBookingKode(bookingKode)
                .orElseThrow(() -> new RuntimeException("Booking tidak ditemukan"));

        if ("CHECK_IN".equalsIgnoreCase(reservation.getStatus())
                || "CHECK_OUT".equalsIgnoreCase(reservation.getStatus())
                || "SELESAI".equalsIgnoreCase(reservation.getStatus())) {
            throw new RuntimeException("Booking sudah di-check-in sebelumnya");
        }

        reservation.setStatus("CHECK_IN");
        reservation.setWaktuCheckIn(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public Reservation checkOut(String bookingKode) {
        Reservation reservation = getReservationByBookingKode(bookingKode)
                .orElseThrow(() -> new RuntimeException("Booking tidak ditemukan"));

        if (!"CHECK_IN".equalsIgnoreCase(reservation.getStatus())) {
            throw new RuntimeException("Booking harus check-in terlebih dahulu");
        }

        reservation.setStatus("SELESAI");
        reservation.setWaktuCheckOut(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private String generateBookingKode() {
        return "SB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
