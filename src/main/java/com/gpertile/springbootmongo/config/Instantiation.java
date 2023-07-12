package com.gpertile.springbootmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gpertile.springbootmongo.domain.Post;
import com.gpertile.springbootmongo.domain.User;
import com.gpertile.springbootmongo.repository.PostRepository;
import com.gpertile.springbootmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null, sdf.parse("11/07/2023"), "Partiu viagem", "Vou viajar pra gyn", alex);
		Post post2 = new Post(null, sdf.parse("12/07/2023"), "Bom dia", "Otimo dia a todos", maria);
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
