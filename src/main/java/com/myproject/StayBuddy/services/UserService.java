package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.deleteById(userId);
    }

}
