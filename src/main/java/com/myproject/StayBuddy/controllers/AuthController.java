package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
}
