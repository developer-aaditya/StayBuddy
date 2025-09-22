package com.myproject.StayBuddy.DTOs;

import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.entities.enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;

    private User bookedUser;

    private Room bookedRoom;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Integer totalPrice;

    private BookingStatus bookingStatus;

}
