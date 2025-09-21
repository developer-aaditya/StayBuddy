package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

}
