package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.BookingDTO;
import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.exceptions.ResourceNotFoundException;
import com.myproject.StayBuddy.repositories.BookingRepository;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoomService roomService;

    public Booking createBooking(Long userId, Booking booking) {
        userService.isExistById(userId);
        return bookingRepository.save(booking);
    }

    public List<BookingDTO> checkBookingByUserId(Long userId) {
        userService.isExistById(userId);
        User user = userRepository.findById(userId).orElse(null);
        List<Booking> bookings = bookingRepository.findBookingByBookedUser(user);
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .toList();
    }

    public BookingDTO findBookingByBookingId(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new ResourceNotFoundException("Booking not found with id : " +bookingId)
        );
        return modelMapper.map(booking, BookingDTO.class);
    }

    public Booking checkInTime(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setCheckInDate(LocalDateTime.now());
        Room bookedRoom = booking.getBookedRoom();
        roomService.setRoomStatus(bookedRoom.getId());
        return booking;
    }

    public Booking checkOutTime(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setCheckOutDate(LocalDateTime.now());
        Room bookedRoom = booking.getBookedRoom();
        roomService.setRoomStatus(bookedRoom.getId());
        return booking;
    }

    public void cancelBookingByBookingId(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        bookingRepository.deleteById(bookingId);
    }
}
