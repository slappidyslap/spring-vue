package io.melakuera.springvue.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.melakuera.springvue.dto.requests.SignInRequest;
import io.melakuera.springvue.dto.requests.SignUpRequest;
import io.melakuera.springvue.dto.requests.UpdateRefreshTokenRequest;
import io.melakuera.springvue.dto.responses.RefreshTokenResponse;
import io.melakuera.springvue.dto.responses.SignInResponse;
import io.melakuera.springvue.models.User;
import io.melakuera.springvue.services.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/sign-in")
	ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest dto) {
		return authService.signIn(dto);
	}
	
	@PostMapping("/sign-up")
	ResponseEntity<User> signUp(@RequestBody SignUpRequest dto) {
		return authService.signUp(dto);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<RefreshTokenResponse> updateRefreshToken(
			@RequestBody UpdateRefreshTokenRequest dto) {
		return authService.updateRefreshToken(dto);
	}
}
