package com.myproject.StayBuddy.controllers;

import com.myproject.StayBuddy.DTOs.LoginDTO;
import com.myproject.StayBuddy.DTOs.SignupDTO;
import com.myproject.StayBuddy.DTOs.TokenDTO;
import com.myproject.StayBuddy.services.AuthService;
import com.myproject.StayBuddy.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @RequestBody SignupDTO signupDTO,
            HttpServletResponse response
    ){
        TokenDTO tokenDTO = authService.signUp(signupDTO);
        String refreshToken = tokenDTO.getRefreshToken();
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(tokenDTO.getAccessToken());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginDTO loginDTO, HttpServletResponse response
    ){
        TokenDTO tokenDTO = authService.login(loginDTO);
        String refreshToken = tokenDTO.getRefreshToken();
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(tokenDTO.getAccessToken());
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(
            HttpServletRequest request, HttpServletResponse response
    ){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            throw new AuthenticationServiceException("Cookies not found. Please login again.");
        }
        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the cookies."));
        TokenDTO tokenDTO = authService.refresh(refreshToken);
        return ResponseEntity.ok(tokenDTO.getAccessToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request, HttpServletResponse response
    ){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if("refreshToken".equals(cookie.getName())){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                }
            }
        }
        return ResponseEntity.ok("Logout Successfully!!");
    }
}
