package com.anuj.springrestservice.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anuj.springrestservice.user.UserDTO;
import com.anuj.springrestservice.user.UserDaoService;
import com.anuj.springrestservice.user.UserNotFoundException;
import com.anuj.springrestservice.user.UserRequest;


/*
 * HTTP Codes:
 * 	GET: 200
 *  POST: 201
 *  DELETE: 204
 *  PUT:
 */
	
@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	@Qualifier("userDaoService")
	private UserDaoService userDaoService;


	@GetMapping(path="/retrieve-all")
	public List<UserDTO> retrieveAllUsers(){
		return userDaoService.findAll();
	}


	@GetMapping(path="/{userId}")
	public ResponseEntity<UserDTO> retrieveSpecificUser(@PathVariable int userId) {
		final Optional<UserDTO> optionUser = userDaoService.findOne(userId);

		ResponseEntity<UserDTO> response = null;
		if(optionUser.isPresent()){
			response = ResponseEntity.ok(optionUser.get());
		}else{
			throw new UserNotFoundException("user not found having userId : " + userId);
		}

		return response;
	}


	// when we create a new user, in response we send the URI of the new user.
	@PostMapping
	public ResponseEntity<Object> createNewUser(@RequestBody UserRequest userRequest){
		final UserDTO savedUser = userDaoService.save(userRequest);

		//uriOfNewAddedUser
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();

		// this URI will be available in the Response header. 
		// Successful status of POST is 201
		return ResponseEntity.created(location).build();
	}

	
	@DeleteMapping(path="/delete/{userId}")
	public ResponseEntity<Object> deleteUserById(@PathVariable int userId){
		final boolean userRemoved = userDaoService.deleteUserById(userId);
		
		if(userRemoved){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else{
			throw new UserNotFoundException("user not found having userId : " + userId);
		}
	}
	
}
