package com.myproject.StayBuddy.DTOs;

import lombok.Data;

@Data
public class SignupDTO {

    private String name;
    private String DOB;
    private String email;
    private String password;
    private String contactNo;
}
