package com.zaju.datamanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.User;
import com.zaju.datamanager.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User updateUser(User user) {
		
		Optional<User> userOptional = userRepository.findById(user.getId());
		
		if ( !userOptional.isPresent() )
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		
		User userToUpdate = userOptional.get();
		
		if( !userToUpdate.getLogin().equals(user.getLogin()) && isUserLoginExists(user.getLogin()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with login: '" + user.getLogin() + "' already exists.");
		
		userToUpdate.setLogin(user.getLogin());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setRole(user.getRole());
		userToUpdate.setUpdateDate(new Date());
		
		return userRepository.save(userToUpdate);
	}
	
	public User createUser(User user) {
		
		if(isUserLoginExists(user.getLogin()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with login: '" + user.getLogin() + "' already exists.");

		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		
		return userRepository.save(user);
	}
	
	public User authenticate(String login, String password) {
		
		Optional<User> userOptional = userRepository.getUserByLogin(login);
		
		if( !userOptional.isPresent() )
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		
		User user = userOptional.get();
		
		if( !user.getPassword().equals(password) )
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
		
		return user;
	}
	
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
		
	private boolean isUserLoginExists(String login) {
		return userRepository.getUserByLogin(login).isPresent();
	}
}
