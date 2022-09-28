package io.melakuera.springvue.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.melakuera.springvue.models.RefreshToken;
import io.melakuera.springvue.models.User;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);
	
	Optional<RefreshToken> findByOwner(User owner);
}
