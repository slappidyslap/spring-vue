package io.melakuera.springvue.services;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.melakuera.springvue.dto.requests.SignInRequest;
import io.melakuera.springvue.dto.requests.SignUpRequest;
import io.melakuera.springvue.dto.requests.UpdateRefreshTokenRequest;
import io.melakuera.springvue.dto.responses.RefreshTokenResponse;
import io.melakuera.springvue.dto.responses.SignInResponse;
import io.melakuera.springvue.exceptions.UserAlreadyExistsException;
import io.melakuera.springvue.exceptions.UserNotFoundException;
import io.melakuera.springvue.models.RefreshToken;
import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.UserRepo;
import io.melakuera.springvue.security.Role;
import io.melakuera.springvue.security.TokenUtils;
import io.melakuera.springvue.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepo userRepo;
	private final AuthenticationManager authenticationManager;
	private final BCryptPasswordEncoder passwordEncoder;
	private final TokenUtils tokenUtils;
	
	@Transactional(readOnly = true)
	public ResponseEntity<SignInResponse> signIn(SignInRequest dto) {
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
		Authentication authentication = 
				authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		String[] roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		
		User foundUser = userRepo.findByUsername(username).orElseThrow(() -> {
			throw new UserNotFoundException(
					"such user by username (%s) not found", username);
		});		  
		String jwt = tokenUtils.generateJwt(username);
		RefreshToken refreshToken = tokenUtils.generateRefreshToken(foundUser);
		
		return ResponseEntity.ok(new SignInResponse(
				jwt,
				refreshToken.getToken(),
				userDetails.getId(),
				userDetails.getEmail(),
				userDetails.getUsername(),
				roles
		));
	}
	
	@Transactional
	public ResponseEntity<User> signUp(SignUpRequest dto) {
		
		if (userRepo.existsByEmail(dto.getEmail()))
			throw new UserAlreadyExistsException("such email is already taken");

		if (userRepo.existsByUsername(dto.getUsername()))
			throw new UserAlreadyExistsException("such username is already taken");
		
		User newUser = new User(
				dto.getUsername(), 
				passwordEncoder.encode(dto.getPassword()),
				dto.getEmail(),
				dto.getUsername().equals("eld") ? Set.of(Role.ROLE_USER, Role.ROLE_ADMIN) : Set.of(Role.ROLE_USER));
		userRepo.save(newUser);
			
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<RefreshTokenResponse> updateRefreshToken(
			UpdateRefreshTokenRequest dto) {
		
		return ResponseEntity.ok(tokenUtils.updateRefreshToken(dto));
	}
}
