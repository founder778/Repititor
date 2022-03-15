package com.example.repititor.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Integer id;
    private String subject;
    private String email;
    private String text;
    private LocalDateTime usedDate;

    private Boolean used = false;
    private LocalDateTime date = LocalDateTime.now();
}
