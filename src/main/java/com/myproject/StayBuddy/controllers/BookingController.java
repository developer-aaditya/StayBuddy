package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.BookingDTO;
import com.myproject.StayBuddy.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("{userId},{roomId}")
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO,@PathVariable Long userId, @PathVariable Long roomId){
        return bookingService.createBooking(userId,roomId,bookingDTO);
    }

    @GetMapping("{userId}")
    public List<BookingDTO> getBookingByUserId(@PathVariable Long userId){
       return bookingService.getBookingByUserId(userId);
    }

    @GetMapping("{bookingId}")
    public BookingDTO getBookingById(@PathVariable Long bookingId){
       return bookingService.getBookingById(bookingId);
    }

    @PostMapping("{bookingId}")
    public BookingDTO checkIn(@PathVariable Long bookingId){
       return bookingService.checkIn(bookingId);
    }

    @PostMapping("{bookingId}")
    public BookingDTO checkOut(@PathVariable Long bookingId){
        return bookingService.checkOut(bookingId);
    }

    @DeleteMapping("{bookingId}")
    public void  cancelBooking(@PathVariable Long bookingId){
       bookingService.cancelBooking(bookingId);
    }

}
