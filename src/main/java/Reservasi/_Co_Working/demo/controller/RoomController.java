package Reservasi._Co_Working.demo.controller;

import java.util.List;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

     @GetMapping("/{id}")
     public Room  getRoomById(@PathVariable  Long id) {
        return roomService.getRoomById(id).orElse(null);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
         return roomService.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @GetMapping("/page")
    public String roomPage(Model model) {
    model.addAttribute("rooms", roomService.getAllRooms());
    return "room";
}
}
