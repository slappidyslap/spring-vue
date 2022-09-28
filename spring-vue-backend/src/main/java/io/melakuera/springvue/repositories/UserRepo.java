package io.melakuera.springvue.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.melakuera.springvue.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
