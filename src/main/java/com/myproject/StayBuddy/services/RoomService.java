package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.RoomDTO;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.exceptions.ResourceNotFoundException;
import com.myproject.StayBuddy.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomDTO createNewRoom(RoomDTO roomDTO){
        Room inputRoomEntity = modelMapper.map(roomDTO, Room.class);
        Room savedUserEntity = roomRepository.save(inputRoomEntity);
        return modelMapper.map(savedUserEntity, RoomDTO.class);
    }

    public RoomDTO deleteRoomByRoomId(Long roomId){
        isRoomExistsById(roomId);
        Room deletedRoom = roomRepository.findById(roomId).orElse(null);
        roomRepository.deleteById(roomId);
        return modelMapper.map(deletedRoom, RoomDTO.class);
    }

    public void setRoomStatus(Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("Room not found with id : " +roomId)
        );
        room.setStatus(!room.getStatus());
        roomRepository.save(room);
    }

    public void isRoomExistsById(Long roomId){
        boolean exists = roomRepository.existsById(roomId);
        if(!exists){
            throw new ResourceNotFoundException("Room not found with id : " +roomId);
        }
    }
}
