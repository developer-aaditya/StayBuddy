package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.UserDTO;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDTO createNewUser(@RequestBody UserDTO userDTO) {
        return userService.createNewUser(userDTO);
    }

    @DeleteMapping("{userId")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
       return userService.getAllUsers();
    }
}
