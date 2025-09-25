package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.RoomDTO;
import com.myproject.StayBuddy.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // Create new room
    @PostMapping
    public ResponseEntity<RoomDTO> createNewRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO savedRoom = roomService.createNewRoom(roomDTO);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    // Get all rooms
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    // Get room by id
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId) {
        RoomDTO room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    // Delete room by id
    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDTO> deleteRoomById(@PathVariable Long roomId) {
        RoomDTO deletedRoom = roomService.deleteRoomById(roomId);
        return ResponseEntity.ok(deletedRoom);
    }

    // Toggle room availability
    @PatchMapping("/{roomId}/availability")
    public ResponseEntity<RoomDTO> toggleRoomAvailability(@PathVariable Long roomId) {
        RoomDTO updatedRoom = roomService.toggleRoomAvailability(roomId);
        return ResponseEntity.ok(updatedRoom);
    }
}
