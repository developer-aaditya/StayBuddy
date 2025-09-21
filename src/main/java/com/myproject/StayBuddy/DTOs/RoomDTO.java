package com.myproject.StayBuddy.DTOs;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private String RoomId;

    private String title;

    private String description;

    private String location;

    private Integer price;

    private Integer maxGuests;

    private Boolean status;

    private User host;

    private Booking roomBooking;
}
