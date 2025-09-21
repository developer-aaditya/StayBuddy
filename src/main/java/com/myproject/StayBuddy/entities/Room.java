package com.myproject.StayBuddy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User host;

    @OneToOne(mappedBy = "bookedRoom", fetch = FetchType.EAGER)
    @JoinColumn
    private Booking roomBooking;
}
