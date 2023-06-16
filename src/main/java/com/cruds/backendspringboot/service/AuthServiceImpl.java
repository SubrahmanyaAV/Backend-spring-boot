package com.cruds.backendspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cruds.backendspringboot.dto.RegisterDTO;
import com.cruds.backendspringboot.dto.UserDTO;
import com.cruds.backendspringboot.entity.User;
import com.cruds.backendspringboot.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(RegisterDTO register) {
//		User user = new User(register.getUserName(),register.getEmail(),new BCryptPasswordEncoder().encode(register.getPassword()));
		User user = new User();
		user.setUserName(register.getUserName());
		user.setEmail(register.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
		User registeredUser = userRepository.save(user);

		UserDTO userDTO = new UserDTO();
		userDTO.setId(registeredUser.getId());
		userDTO.setEmail(registeredUser.getEmail());
		userDTO.setUserName(registeredUser.getUserName());
		return userDTO;
	}

}
