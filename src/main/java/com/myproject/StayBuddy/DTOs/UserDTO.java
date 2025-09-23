package com.myproject.StayBuddy.DTOs;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.Room;
import com.myproject.StayBuddy.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {


    private Long id;

    private String name;
    private LocalDate dob;


    private String email;
    private String password;
    private String contactNo;

    private Role role;


    private LocalDateTime createdAt;

    private Set<Booking> userBookings = new HashSet<>();

    private Set<Room> hostRooms = new HashSet<>();
}
