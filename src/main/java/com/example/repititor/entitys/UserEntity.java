package com.example.repititor.entitys;

import com.example.repititor.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private String parol;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
