package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.BookingDTO;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.repositories.UserRepository;
import com.myproject.StayBuddy.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    // Create booking
    @PostMapping("/{roomId}")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO,
                                                    @PathVariable Long roomId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByName(username);
        Long userId = user.getId();

        BookingDTO savedBooking = bookingService.createBooking(userId, roomId, bookingDTO);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    // Get bookings by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingByUserId(userId));
    }

    // Get booking by bookingId
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    // Check-in
    @PatchMapping("/{bookingId}/check-in")
    public ResponseEntity<BookingDTO> checkIn(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.checkIn(bookingId));
    }

    // Check-out
    @PatchMapping("/{bookingId}/check-out")
    public ResponseEntity<BookingDTO> checkOut(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.checkOut(bookingId));
    }

    // Cancel booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
