package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
}
