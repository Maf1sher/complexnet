package com.mafisher.complexnet.mappers.impl;

import com.mafisher.complexnet.domain.dto.PostRequest;
import com.mafisher.complexnet.domain.entity.Post;
import com.mafisher.complexnet.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostRequestMapperImpl implements Mapper<Post, PostRequest> {
    @Override
    public PostRequest toDto(Post post) {
        return PostRequest.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    @Override
    public Post toEntity(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();
    }
}
