package Reservasi_Co_Working.demo.repository;

import Reservasi_Co_Working.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}