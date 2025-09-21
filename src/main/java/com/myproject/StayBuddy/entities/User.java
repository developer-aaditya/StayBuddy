package com.myproject.StayBuddy.entities;

import com.myproject.StayBuddy.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate DOB;

    @Column(unique = true)
    private String email;
    private String password;
    private String contactNo;
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany
    private Set<Room> rooms = new HashSet<>();
}
