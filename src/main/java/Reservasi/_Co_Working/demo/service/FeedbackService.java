package Reservasi._Co_Working.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Feedback;
import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.FeedbackRepository;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReservationService reservationService;

    public FeedbackService(FeedbackRepository feedbackRepository, ReservationService reservationService) {
        this.feedbackRepository = feedbackRepository;
        this.reservationService = reservationService;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback submitFeedback(User user, Long reservationId, int rating, String komentar) {
        Reservation reservation = reservationService.getReservationById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservasi tidak ditemukan"));

        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Anda tidak berhak memberi feedback untuk reservasi ini");
        }

        if (!"SELESAI".equalsIgnoreCase(reservation.getStatus())) {
            throw new RuntimeException("Feedback hanya bisa diberikan setelah check-out selesai");
        }

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setReservation(reservation);
        feedback.setRating(rating);
        feedback.setKomentar(komentar);
        feedback.setTanggal(LocalDate.now());
        return feedbackRepository.save(feedback);
    }
}
