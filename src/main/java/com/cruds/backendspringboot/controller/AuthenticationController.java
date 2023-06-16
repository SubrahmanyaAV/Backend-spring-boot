package com.cruds.backendspringboot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cruds.backendspringboot.dto.AuthenticationDTO;
import com.cruds.backendspringboot.dto.AuthenticationResponse;
import com.cruds.backendspringboot.service.UserService;
import com.cruds.backendspringboot.util.JWTUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO,
			HttpServletResponse response)
			throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
					authenticationDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password!");
		} catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
			return null;
		}

		final UserDetails userDetails = userService.loadUserByUsername(authenticationDTO.getEmail());

		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		return new AuthenticationResponse(jwt);

	}
}
