package Reservasi._Co_Working.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Reservasi._Co_Working.demo.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
