package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.BookingDTO;
import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.exceptions.ResourceNotFoundException;
import com.myproject.StayBuddy.repositories.BookingRepository;
import com.myproject.StayBuddy.repositories.RoomRepository;
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
    private final ModelMapper modelMapper;
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public BookingDTO createBooking(Long userId, Long roomId, BookingDTO bookingDTO) {
        User user = userService.getUserOrThrow(userId);
        Room room = roomService.getRoomOrThrow(roomId);

        if (!room.getIsAvailable()) {
            throw new IllegalArgumentException("Room is currently not available for booking");
        }

        Booking booking =  modelMapper.map(bookingDTO, Booking.class);
        booking.setBookedUser(user);
        booking.setBookedRoom(room);
        room.setIsAvailable(false);
        roomService.toggleRoomAvailability(room.getId());

        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    public List<BookingDTO> getBookingByUserId(Long userId){
        User user = userService.getUserOrThrow(userId);
        return bookingRepository.findBookingByBookedUser(user).stream()
                .map(b -> modelMapper.map(b,BookingDTO.class))
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found with id: "+bookingId));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public BookingDTO checkIn(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: "+bookingId));
        booking.setCheckInDate(LocalDateTime.now());

        Room room = booking.getBookedRoom();
        if(room != null && room.getIsAvailable()){
            room.setIsAvailable(false);
        }

        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking , BookingDTO.class);
    }

    public BookingDTO checkOut(Long bookingId){
            Booking booking= bookingRepository.findById(bookingId)
                    .orElseThrow(()-> new ResourceNotFoundException("Booking not found with id: "+bookingId));
            booking.setCheckOutDate(LocalDateTime.now());

            Room room = booking.getBookedRoom();
            if (room != null){
                room.setIsAvailable(true);
            }

            Booking savedBooking = bookingRepository.save(booking);
            return modelMapper.map(savedBooking , BookingDTO.class);
    }

    public void  cancelBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found with id: "+bookingId));

        Room room = booking.getBookedRoom();
        if(room!= null){
            room.setIsAvailable(true);
            roomRepository.save(room);
        }
        bookingRepository.delete(booking);
    }
}
