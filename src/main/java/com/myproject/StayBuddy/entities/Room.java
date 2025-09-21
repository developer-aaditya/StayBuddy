package com.myproject.StayBuddy.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String RoomId;

    private String title;

    private String description;

    private String location;

    private Integer price;

    private Integer maxGuests;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean status;

    @ManyToOne
    @JoinColumn
    private User host;

    @OneToOne(mappedBy = "room", fetch = FetchType.EAGER)
    @JoinColumn
    private Booking booking;
}
