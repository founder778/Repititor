package com.example.repititor.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CommentDto {
    private Integer c_id;
    private String c_content;
    private Integer user_id;
    private Integer post_id;
    private LocalDateTime dateTime;
}
