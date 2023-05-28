package com.spring.backend.easyvet.controller;


import com.spring.backend.easyvet.dto.EmailDTO;
import com.spring.backend.easyvet.dto.ResetPasswordDTO;
import com.spring.backend.easyvet.model.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.JwtAuthResponseDTO;
import com.spring.backend.easyvet.dto.LoginDTO;
import com.spring.backend.easyvet.security.JwtTokenProvider;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private LoginServiceImpl loginService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){	
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateAccessToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthResponseDTO(token));
	}
	@PostMapping("/verify-email")
	public ResponseEntity<?> verifyEmail(@RequestBody EmailDTO emailDTO) {
		String partialDni = loginService.verifyEmail(emailDTO.getEmail());
		return ResponseEntity.ok(partialDni);
	}


	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		loginService.resetPassword(resetPasswordDTO);
		return ResponseEntity.ok().build();
	}


}
