package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.RoomDTO;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.exceptions.ResourceNotFoundException;
import com.myproject.StayBuddy.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomDTO createNewRoom(RoomDTO roomDTO) {
        Room room = modelMapper.map(roomDTO, Room.class);
        Room savedRoom = roomRepository.save(room);
        return modelMapper.map(savedRoom, RoomDTO.class);
    }

    public RoomDTO deleteRoomById(Long roomId) {
        Room room = getRoomOrThrow(roomId);
        roomRepository.delete(room);
        return modelMapper.map(room, RoomDTO.class);
    }

    public RoomDTO toggleRoomAvailability(Long roomId) {
        Room room = getRoomOrThrow(roomId);
        room.setIsAvailable(!room.getIsAvailable());
        Room savedRoom = roomRepository.save(room);
        return modelMapper.map(savedRoom, RoomDTO.class);
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public Room getRoomOrThrow(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
    }
}
