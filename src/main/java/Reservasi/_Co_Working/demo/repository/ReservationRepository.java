package Reservasi._Co_Working.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Reservasi._Co_Working.demo.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByBookingKode(String bookingKode);

    List<Reservation> findByUserId(Long userId);

    @Query("SELECT r.meja.id FROM Reservation r WHERE r.tanggal = :tanggal AND r.status NOT IN ('DIBATALKAN', 'SELESAI')")
    List<Long> findBookedMejaIdsByTanggal(@Param("tanggal") LocalDate tanggal);

    boolean existsByMejaIdAndTanggalAndStatusNotIn(Long mejaId, LocalDate tanggal, List<String> statuses);
}
