package com.example.repititor.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer u_id;
    private String u_name;
    private String u_surname;
    private String u_phone;
    private String u_email;
    private String u_parol;
    private String u_login;
}
