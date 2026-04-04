package com.gianlu.finance.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gianlu.finance.dto.request.LoginRequest;
import com.gianlu.finance.dto.response.AuthResponse;
import com.gianlu.finance.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public void register(@RequestBody @Valid RegisterRequest request) {
		authService.register(request);
		
	}
	@PostMapping("/login")
	public AuthResponse login(@RequestBody @Valid LoginRequest request) {
		return authService.login(request);
		
	}
	

}
