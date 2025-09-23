package com.myproject.StayBuddy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomId;

    private String title;
    @Column(length = 2000)
    private String description;

    private String location;

    private BigDecimal price;

    private Integer maxGuests;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    private Boolean isAvailable;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User host;

    @OneToOne(mappedBy = "bookedRoom")
    private Booking roomBooking;
}
