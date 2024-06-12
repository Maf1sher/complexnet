package com.mafisher.complexnet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;

    private String title;

    private String content;

    private String author_email;

    private LocalDateTime createdAt;

    private LocalDateTime updated_at;
}
