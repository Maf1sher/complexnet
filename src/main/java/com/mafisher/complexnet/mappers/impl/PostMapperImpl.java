package com.mafisher.complexnet.mappers.impl;

import com.mafisher.complexnet.domain.dto.PostDto;
import com.mafisher.complexnet.domain.entity.Post;
import com.mafisher.complexnet.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostMapperImpl implements Mapper<Post, PostDto> {

    private ModelMapper modelMapper;

    @Override
    public PostDto toDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public Post toEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
