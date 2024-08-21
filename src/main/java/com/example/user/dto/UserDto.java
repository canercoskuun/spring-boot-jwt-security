package com.example.user.dto;

import com.example.user.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
