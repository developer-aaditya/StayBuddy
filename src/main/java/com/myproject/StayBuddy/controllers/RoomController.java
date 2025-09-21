package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
}
