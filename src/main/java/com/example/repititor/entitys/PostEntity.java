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
public class PostEntity extends BaseEntity {
    private String a_title;
    private String a_content;
    private String a_price;
    private String a_type;
    private String a_img;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private PostStatus status;
}
