package com.example.repititor.controller;

import com.example.repititor.dto.UserDto;
import com.example.repititor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }
}
