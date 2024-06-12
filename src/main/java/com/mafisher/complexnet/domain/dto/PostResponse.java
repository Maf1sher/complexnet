package com.mafisher.complexnet.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostResponse {
    private Long id;

    private String title;

    private String content;

    private String author_email;

    private String author_name;

    private LocalDateTime createdAt;

    private LocalDateTime updated_at;
}
