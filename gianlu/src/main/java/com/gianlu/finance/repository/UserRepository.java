package com.gianlu.finance.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gianlu.finance.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	

}
