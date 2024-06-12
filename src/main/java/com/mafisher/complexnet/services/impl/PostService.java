package com.mafisher.complexnet.services.impl;

import com.mafisher.complexnet.domain.entity.Post;
import com.mafisher.complexnet.domain.entity.User;
import com.mafisher.complexnet.repositories.PostRepository;
import com.mafisher.complexnet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public Post save(Post postRequest, UserDetails userDetail) throws UsernameNotFoundException {
        String email = userDetail.getUsername();
        User author = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User " + email + " not found")
        );
        postRequest.setAuthor(author);
        return postRepository.save(postRequest);
    }


    public Optional<Post> findOne(Long id) {

        return postRepository.findById(id);
    }


    public List<Post> findAll() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public boolean isExists(Long id) {
        return postRepository.existsById(id);
    }

    public Post patrialUpdate(Long id, Post post, UserDetails userDetail) {
        post.setId(id);
        return postRepository.findById(id).map(existingPost -> {
            Optional.ofNullable(post.getTitle()).ifPresent(existingPost::setTitle);
            Optional.ofNullable(post.getContent()).ifPresent(existingPost::setContent);
            return postRepository.save(existingPost);
        }).orElseThrow(() ->new RuntimeException("Post not found"));
    }

    public void deletePost(Long id, UserDetails userDetail) {
         postRepository.deleteById(id);
    }
}
