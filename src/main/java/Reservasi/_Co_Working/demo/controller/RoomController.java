package Reservasi._Co_Working.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Reservasi._Co_Working.demo.model.Room;
import Reservasi._Co_Working.demo.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/page")
    public String roomsPage(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("roomForm", new Room());
        return "rooms";
    }

    @GetMapping("/page/edit/{id}")
    public String editRoomPage(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id)
                .orElseThrow(() -> new RuntimeException("Ruangan tidak ditemukan"));
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("roomForm", room);
        model.addAttribute("editMode", true);
        return "rooms";
    }

    @PostMapping("/page")
    public String createRoom(@ModelAttribute Room roomForm) {
        roomService.saveRoom(roomForm);
        return "redirect:/rooms/page";
    }

    @PostMapping("/page/update/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute Room roomForm) {
        roomService.updateRoom(id, roomForm);
        return "redirect:/rooms/page";
    }

    @PostMapping("/page/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms/page";
    }

    @GetMapping
    @ResponseBody
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id).orElse(null);
    }

    @PostMapping
    @ResponseBody
    public Room createRoomApi(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Room updateRoomApi(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRoomApi(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
