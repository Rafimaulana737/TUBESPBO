package Reservasi._Co_Working.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.repository.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room tidak ditemukan"));

        room.setNamaRuangan(roomDetails.getNamaRuangan());
        room.setKapasitas(roomDetails.getKapasitas());

        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}

