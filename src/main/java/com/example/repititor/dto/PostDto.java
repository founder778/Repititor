package com.example.repititor.dto;

import com.example.repititor.enums.PostStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private String price;
    private String type;
    private String img;
    private LocalDate date;
    private Integer user_id;
}
