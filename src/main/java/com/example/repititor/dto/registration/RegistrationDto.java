package com.example.repititor.dto.registration;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationDto {
    @NotNull
    @NotEmpty(message = "Bu maydon to`ldrilishi shart")
    private String name;
    @NotNull
    @NotEmpty(message = "Bu maydon to`ldrilishi shart")
    private String surname;
    @NotNull
    @NotEmpty(message = "Bu maydon to`ldrilishi shart")
    private String email;
    @NotNull
    @NotEmpty(message = "Bu maydon to`ldrilishi shart")
    private String phone;
    @NotNull
    @NotEmpty(message = "Bu maydon to`ldrilishi shart")
    private String password;
}
