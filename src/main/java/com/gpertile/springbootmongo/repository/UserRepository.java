package com.gpertile.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gpertile.springbootmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
