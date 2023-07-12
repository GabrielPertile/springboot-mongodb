package com.gpertile.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpertile.springbootmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
