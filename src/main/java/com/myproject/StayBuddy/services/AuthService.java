package com.myproject.StayBuddy.services;

import com.myproject.StayBuddy.DTOs.LoginDTO;
import com.myproject.StayBuddy.DTOs.SignupDTO;
import com.myproject.StayBuddy.DTOs.TokenDTO;
import com.myproject.StayBuddy.entities.User;
import com.myproject.StayBuddy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO signUp(SignupDTO signupDTO) {
        String email = signupDTO.getEmail();
        User user = userService.getUserByEmail(email);
        if(user != null){
            throw new IllegalArgumentException("User with email : " +email+ " is already exists");
        }
        User newUser = modelMapper.map(signupDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        User savedUser = userRepository.save(newUser);
        String accessToken = jwtService.generateAccessToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDTO refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromRefreshToken(refreshToken);
        User user = userService.getUserOrThrow(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
