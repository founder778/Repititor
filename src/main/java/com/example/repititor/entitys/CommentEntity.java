package com.example.repititor.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    private String c_content;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;


}
