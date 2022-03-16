package com.example.repititor.controller;

import com.example.repititor.dto.UserDto;
import com.example.repititor.dto.auth.AuthorizationDto;
import com.example.repititor.dto.registration.RegistrationDto;
import com.example.repititor.service.AuthService;
import com.example.repititor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
    UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> auth(@RequestBody AuthorizationDto dto) {
        UserDto response = userService.authorization(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> create(@RequestBody RegistrationDto dto){
        userService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> ver(@PathVariable("jwt") String id){
        userService.verification(id);
        System.out.println(id);
        return ResponseEntity.ok("Sucsesfully authorization");
    }


}
