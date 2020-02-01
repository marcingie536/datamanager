package com.zaju.datamanager.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.User;
import com.zaju.datamanager.dto.UserDTO;
import com.zaju.datamanager.service.UserService;
import com.zaju.datamanager.utils.RequestValidation;

@RestController
@RequestMapping("/api/authorization/")
public class LoginController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	
	public LoginController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("/login")
	public UserDTO login(@RequestParam String login, @RequestParam String password) {
		
		User user = userService.authenticate(login, password);
			
		return modelMapper.map(user, UserDTO.class);
	}
	
	@GetMapping("/getAllUsers")
	public List<UserDTO> getAllUsers() {
		
		List<User> users = userService.getAllUsers();
				
		List<UserDTO> usersResult = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		
		return usersResult;
	}
	
	@PostMapping("/createUser")
	public UserDTO createUser(@RequestBody UserDTO user) {
		
		if ( !RequestValidation.isUserDTOValid(user) ) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not valid");
		
		User newUser = modelMapper.map(user, User.class);
		
		userService.createUser(newUser);		
		
		return modelMapper.map(newUser, UserDTO.class);
	}
	
	@PutMapping("/updateUser")
	public UserDTO updateUser(@RequestBody UserDTO user) {
		
		if ( !RequestValidation.isUserDTOValid(user) ) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not valid");
		
		User userToUpdate = modelMapper.map(user, User.class);
		
		userService.updateUser(userToUpdate);

		return modelMapper.map(userToUpdate, UserDTO.class);
	}

}
