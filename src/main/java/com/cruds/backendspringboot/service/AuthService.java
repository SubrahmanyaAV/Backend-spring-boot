package com.cruds.backendspringboot.service;

import com.cruds.backendspringboot.dto.RegisterDTO;
import com.cruds.backendspringboot.dto.UserDTO;

public interface AuthService {

	UserDTO createUser(RegisterDTO register);

}
