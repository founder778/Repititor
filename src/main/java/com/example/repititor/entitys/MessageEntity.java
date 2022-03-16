package com.example.repititor.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity(name = "message")
@Setter
@Getter
@NoArgsConstructor
public class MessageEntity extends BaseEntity {
    private String email;
    private String subject;
    private String content;
    private Boolean used = false;
    private LocalDateTime usedDate;

}
