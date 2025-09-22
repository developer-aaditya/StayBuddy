package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.repositories.BookingRepository;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public Booking createBooking(Long userId, Booking booking) {
        userService.isExistById(userId);
        return bookingRepository.save(booking);
    }

    public List<Booking> checkBookingByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        return user.getUserBookings().stream().toList();
    }

    public Booking checkInTime(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setCheckInDate(LocalDateTime.now());
        return booking;
    }

    public Booking checkOutTime(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setCheckOutDate(LocalDateTime.now());
        return booking;
    }

    public void cancelBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        bookingRepository.deleteById(bookingId);
    }
}
