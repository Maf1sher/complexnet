package com.mafisher.complexnet.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {

    @NotEmpty(message = "Title is mandatory")
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Content is mandatory")
    @NotBlank(message = "Content is mandatory")
    private String content;

}
