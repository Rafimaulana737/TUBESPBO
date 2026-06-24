package Reservasi._Co_Working.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Report;
import Reservasi._Co_Working.demo.model.Reservation;
import Reservasi._Co_Working.demo.model.User;
import Reservasi._Co_Working.demo.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReservationService reservationService;

    public ReportService(ReportRepository reportRepository, ReservationService reservationService) {
        this.reportRepository = reportRepository;
        this.reservationService = reservationService;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report submitReport(User user, Long reservationId, String deskripsiKerusakan) {
        Reservation reservation = reservationService.getReservationById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservasi tidak ditemukan"));

        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Anda tidak berhak mengirim laporan untuk reservasi ini");
        }

        Report report = new Report();
        report.setUser(user);
        report.setReservation(reservation);
        report.setDeskripsiKerusakan(deskripsiKerusakan);
        report.setStatus("PENDING");
        report.setTanggal(LocalDate.now());
        return reportRepository.save(report);
    }

    public Report updateStatus(Long id, String status) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laporan tidak ditemukan"));
        report.setStatus(status);
        return reportRepository.save(report);
    }
}
