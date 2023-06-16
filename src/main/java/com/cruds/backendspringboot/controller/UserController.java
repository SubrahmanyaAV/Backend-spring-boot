package com.cruds.backendspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cruds.backendspringboot.dto.RegisterDTO;
import com.cruds.backendspringboot.dto.UserDTO;
import com.cruds.backendspringboot.service.AuthService;

@RestController
public class UserController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO register) {
		UserDTO user = authService.createUser(register);
		
		if(user == null)
		{
			return new ResponseEntity<>("Invalid Credentials",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}

}
