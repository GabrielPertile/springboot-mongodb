package com.gpertile.springbootmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpertile.springbootmongo.domain.Post;
import com.gpertile.springbootmongo.repository.PostRepository;
import com.gpertile.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post	= repository.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException(id));
	}
}
