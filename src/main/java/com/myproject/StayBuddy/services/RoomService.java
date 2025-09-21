package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.repositories.BookingRepository;
import com.myproject.StayBuddy.repositories.RoomRepository;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

}
