package Reservasi._Co_Working.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Meja;
import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.repository.MejaRepository;

@Service
public class MejaService {

    private final MejaRepository mejaRepository;
    private final RoomService roomService;

    public MejaService(MejaRepository mejaRepository, RoomService roomService) {
        this.mejaRepository = mejaRepository;
        this.roomService = roomService;
    }

    public List<Meja> getAllMeja() {
        return mejaRepository.findAll();
    }

    public Optional<Meja> getMejaById(Long id) {
        return mejaRepository.findById(id);
    }

    public Meja saveMeja(Meja meja) {
        Room room = roomService.getRoomById(meja.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Ruangan tidak ditemukan"));
        meja.setRoom(room);
        return mejaRepository.save(meja);
    }

    public Meja updateMeja(Long id, Meja mejaDetails) {
        Meja meja = mejaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meja tidak ditemukan"));

        Room room = roomService.getRoomById(mejaDetails.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Ruangan tidak ditemukan"));

        meja.setNomorMeja(mejaDetails.getNomorMeja());
        meja.setStatus(mejaDetails.getStatus());
        meja.setRoom(room);
        return mejaRepository.save(meja);
    }

    public void deleteMeja(Long id) {
        mejaRepository.deleteById(id);
    }
}
