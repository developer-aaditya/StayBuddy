package com.myproject.StayBuddy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDateTime;

@Entity
@Data
public class Room {

    @Id
    private Long id;

    private String title;

    private String description;

    private String location;

    private Integer price;

    private Integer maxGuests;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Boolean status;

    @ManyToOne
    private User user;

}
