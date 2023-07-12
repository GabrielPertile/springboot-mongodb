package com.gpertile.springbootmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpertile.springbootmongo.domain.User;
import com.gpertile.springbootmongo.dto.UserDTO;
import com.gpertile.springbootmongo.repository.UserRepository;
import com.gpertile.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repository.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ObjectNotFoundException(id);
		}
	}

	public User update(User obj) {

		try {
			Optional<User> entity = repository.findById(obj.getId());
			User user = entity.get();
			updateData(user, obj);

			return repository.save(user);
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(obj.getId());
		}
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}	
}
