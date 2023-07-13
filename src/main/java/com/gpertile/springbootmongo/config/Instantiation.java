package com.gpertile.springbootmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gpertile.springbootmongo.domain.Post;
import com.gpertile.springbootmongo.domain.User;
import com.gpertile.springbootmongo.dto.AuthorDTO;
import com.gpertile.springbootmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("11/07/2023"), "Partiu viagem", "Vou viajar pra gyn", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("12/07/2023"), "Bom dia", "Otimo dia a todos", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa vigem", sdf.parse("21/07/2023"), new AuthorDTO(alex));

		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("11/07/2023"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Ótimo dia", sdf.parse("13/07/2023"), new AuthorDTO(alex));
	
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPost().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);
	}

}
