package com.example.repititor.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String parol;
    private String jwt;
}
