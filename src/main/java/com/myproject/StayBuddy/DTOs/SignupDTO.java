package com.myproject.StayBuddy.DTOs;

import com.myproject.StayBuddy.entities.enums.Role;
import lombok.Data;

@Data
public class SignupDTO {

    private String name;
    private String dob;
    private String email;
    private String password;
    private String contactNo;
    private Role role;
}
