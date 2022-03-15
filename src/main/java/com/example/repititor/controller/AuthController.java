package com.example.repititor.controller;

import com.example.repititor.dto.UserDto;
import com.example.repititor.dto.auth.AuthorizationDto;
import com.example.repititor.dto.registration.RegistrationDto;
import com.example.repititor.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> auth(@RequestBody AuthorizationDto dto) {
        UserDto response = authService.authorization(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> create(@Valid @RequestBody RegistrationDto dto){
        RegistrationDto registrationDto = authService.create(dto);
        return ResponseEntity.ok(registrationDto);
    }

    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> ver(@PathVariable("jwt") String id){
        authService.verification(id);
        System.out.println(id);
        return ResponseEntity.ok().build();
    }



//    @GetMapping("update/{email}/{name}")
//    public ResponseEntity<?> updateName(@PathVariable("email") String email,@PathVariable("name") String name){
//        String profile = authService.UpdateNameByEmail(name, email);
//        return ResponseEntity.ok(profile);
//    }

}
