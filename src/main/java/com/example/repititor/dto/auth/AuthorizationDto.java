package com.example.repititor.dto.auth;

import lombok.Data;

@Data
public class AuthorizationDto {
    private String phoneOrEmail;
    private String password;
}
