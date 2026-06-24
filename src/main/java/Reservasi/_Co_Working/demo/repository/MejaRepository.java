package Reservasi._Co_Working.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Reservasi._Co_Working.demo.model.Meja;

public interface MejaRepository extends JpaRepository<Meja, Long> {
    List<Meja> findByRoomId(Long roomId);
}
