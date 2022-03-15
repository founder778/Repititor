package com.example.repititor.dto;

import com.example.repititor.enums.PostStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PostDto {
    private Integer a_id;
    private String a_title;
    private String a_content;
    private String a_price;
    private String a_type;
    private String a_img;
    private LocalDate date;
    private Integer user_id;
    private PostStatus status;
}
