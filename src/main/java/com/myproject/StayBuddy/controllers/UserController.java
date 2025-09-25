package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.UserDTO;
import com.myproject.StayBuddy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // plural is more RESTful
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create new user
    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.createNewUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserByUserId(userId);
        return ResponseEntity.ok(user);
    }
}
