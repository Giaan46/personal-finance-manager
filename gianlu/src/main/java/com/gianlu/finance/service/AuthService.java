package com.gianlu.finance.service;



import java.time.LocalDateTime;




import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gianlu.finance.model.enums.Role;
import com.gianlu.finance.dto.request.LoginRequest;
import com.gianlu.finance.dto.request.RegisterRequest;
import com.gianlu.finance.dto.response.AuthResponse;
import com.gianlu.finance.model.entity.User;
import com.gianlu.finance.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//validate if email exist
	public void register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			
		}
		// create user 
		User user = User.builder()
				.email(request.getEmail())
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.createdAt(LocalDateTime.now())
				.build();
		
		// save in data base
		userRepository.save(user);
		
	}
	public AuthResponse login(LoginRequest request) {
		
		// search user by email
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		// validate password
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
			
		}
		// responce simple (later go to JWT
		return new AuthResponse("Login successful");
		
	}

}
