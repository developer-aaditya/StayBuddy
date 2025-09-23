package com.myproject.StayBuddy.entities;

import com.myproject.StayBuddy.entities.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User bookedUser;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room bookedRoom;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Integer totalPrice;


    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @CreationTimestamp
    private LocalDateTime createdTime;
}
