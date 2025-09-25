package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.RoomDTO;
import com.myproject.StayBuddy.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public RoomDTO createNewRoom(@RequestBody RoomDTO roomDTO) {
      return roomService.createNewRoom(roomDTO);
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping("{roomId}")
    public RoomDTO deleteRoomById(@PathVariable Long roomId) {
        return roomService.deleteRoomById(roomId);
    }
}
