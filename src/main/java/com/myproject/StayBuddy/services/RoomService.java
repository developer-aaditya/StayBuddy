package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.RoomDTO;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

//    public RoomDTO createRoomByHosts(RoomDTO roomDTO, Long userId){}
}
