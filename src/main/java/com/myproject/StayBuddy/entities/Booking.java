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

    @ManyToOne
    @JoinColumn
    private User bookedUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Room bookedRoom;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Integer totalPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @CreationTimestamp
    private LocalDateTime createdTime;
}
