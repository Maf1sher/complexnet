package com.mafisher.complexnet.mappers.impl;

import com.mafisher.complexnet.domain.dto.PostResponse;
import com.mafisher.complexnet.domain.entity.Post;
import com.mafisher.complexnet.domain.entity.User;
import com.mafisher.complexnet.mappers.Mapper;
import com.mafisher.complexnet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostResponseMapperImpl implements Mapper<Post, PostResponse> {

    UserRepository userRepository;

    @Override
    public PostResponse toDto(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author_email(post.getAuthor().getEmail())
                .author_name(post.getAuthor().getFullName())
                .createdAt(post.getCreatedAt())
                .updated_at(post.getUpdated_at())
                .build();
    }

    @Override
    public Post toEntity(PostResponse postResponse) {
        User author = userRepository.findByEmail(postResponse.getAuthor_email()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return Post.builder()
                .id(postResponse.getId())
                .title(postResponse.getTitle())
                .content(postResponse.getContent())
                .author(author)
                .createdAt(postResponse.getCreatedAt())
                .updated_at(postResponse.getUpdated_at())
                .build();
    }
}
