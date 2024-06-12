package com.mafisher.complexnet.repositories;

import com.mafisher.complexnet.domain.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends CrudRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

}
