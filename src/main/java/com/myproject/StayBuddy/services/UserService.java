package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.exceptions.ResourceNotFoundException;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId){
        isExistById(userId);
        userRepository.deleteById(userId);
    }

//    public String createNewPassword(String oldPassword, String newPassword){
//        SecurityContextHolder
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User not found with id : " +userId)
//        );
//    }

    public void isExistById(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists) throw new ResourceNotFoundException("User not found with id : " +userId);
    }

}
