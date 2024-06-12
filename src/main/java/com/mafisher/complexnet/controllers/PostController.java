package com.mafisher.complexnet.controllers;

import com.mafisher.complexnet.domain.dto.PostRequest;
import com.mafisher.complexnet.domain.dto.PostResponse;
import com.mafisher.complexnet.domain.entity.Post;
import com.mafisher.complexnet.mappers.Mapper;
import com.mafisher.complexnet.services.impl.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Tag(name = "Posts")
public class PostController {

    private Mapper<Post, PostResponse> responseMapper;
    private Mapper<Post, PostRequest> requestMapper;

    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostResponse> createPost(
            @RequestBody @Valid PostRequest postRequest,
            @AuthenticationPrincipal UserDetails userDetail)
            throws UsernameNotFoundException {
        Post post = requestMapper.toEntity(postRequest);
        Post savedPost = postService.save(post, userDetail);
        PostResponse response = responseMapper.toDto(savedPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public Page<PostResponse> getAllPosts(Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        return posts.map(responseMapper::toDto);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {
        Optional<Post> foundPost = postService.findOne(id);
        return foundPost.map(post ->{
            PostResponse postDto = responseMapper.toDto(post);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostResponse> fullUpdatePost(
            @PathVariable("id") Long id,
            @RequestBody @Valid PostRequest postRequest,
            @AuthenticationPrincipal UserDetails userDetail
    ) throws UsernameNotFoundException {
        if(!postService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = requestMapper.toEntity(postRequest);
        post.setId(id);
        Post savedPost = postService.save(post, userDetail);
        return new ResponseEntity<>(responseMapper.toDto(savedPost), HttpStatus.OK);
    }

    @PatchMapping("/post/{id}")
    public ResponseEntity<PostResponse> partialUpdatePost(
            @PathVariable("id") Long id,
            @RequestBody PostRequest postRequest,
            @AuthenticationPrincipal UserDetails userDetail
    ){
        if(!postService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = requestMapper.toEntity(postRequest);
        Post updatedPost = postService.patrialUpdate(id, post, userDetail);
        return new ResponseEntity<>(responseMapper.toDto(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity deletePost(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetails userDetail
    ){
        postService.deletePost(id, userDetail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
