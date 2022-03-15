package com.example.repititor.entitys;

import com.example.repititor.enums.PostStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "announcment")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer a_id;
    private String a_title;
    private String a_content;
    private String a_price;
    private String a_type;
    private String a_img;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private PostStatus status;
}
