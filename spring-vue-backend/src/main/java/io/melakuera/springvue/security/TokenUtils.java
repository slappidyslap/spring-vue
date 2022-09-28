package io.melakuera.springvue.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.melakuera.springvue.dto.requests.UpdateRefreshTokenRequest;
import io.melakuera.springvue.dto.responses.RefreshTokenResponse;
import io.melakuera.springvue.exceptions.TokenException;
import io.melakuera.springvue.models.RefreshToken;
import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.RefreshTokenRepo;
import io.melakuera.springvue.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Component
@RequiredArgsConstructor
@Log
public class TokenUtils {
	
	private final RefreshTokenRepo refreshTokenRepo;
	private final UserService userService;
	
	@Value("${app.access-token-expiration-ms}") // если в переменной среды не задан, то по умолчанию 30 секунд
	private long accessTokenExpirationMs;
	@Value("${app.refresh-token-expiration-ms}") // по умолчанию 1 минута
	private long refreshTokenExpirationMs;
	@Value("${app.secret-key")
	private String secretKey;

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateJwt(String username) {
		
		Date issuedAt = Date.from(Instant.now());
		Date expiration = Date.from(Instant.now().plusMillis(accessTokenExpirationMs));
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(issuedAt)
				.setExpiration(expiration)
				.signWith(key)
				.compact();
	}
	
	public String getUsernameByJwt(String jwt) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(jwt).getBody().getSubject();
		} catch (Exception e) {
			throw new TokenException("not valid jwt: %s", e.getMessage());
		}
	}
	
	public boolean isValidJwt(String jwt) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			log.warning(e.getMessage());
			return false;
		}
	}
	
	public String getJwtFromHeader(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return "";
	}


	@Transactional(readOnly = true)
	public RefreshToken getRefreshTokenByToken(String token) {
		return refreshTokenRepo.findByToken(token).orElseThrow(() -> {
			throw new TokenException("such refresh token not found");
		});
	}

	@Transactional
	public RefreshToken generateRefreshToken(User user) {
		
		Optional<RefreshToken> expectedRefreshToken = refreshTokenRepo.findByOwner(user);
		
		return expectedRefreshToken.map(refreshToken -> {
			refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpirationMs));
			refreshToken.setToken(UUID.randomUUID().toString());
			
			return refreshTokenRepo.saveAndFlush(refreshToken);
		}).orElseGet(() -> {
			return refreshTokenRepo.save(new RefreshToken(
					userService.getById(user.getId()),
					UUID.randomUUID().toString(),
					Instant.now().plusMillis(refreshTokenExpirationMs)));
		});
	}

	@Transactional
	public RefreshTokenResponse updateRefreshToken(UpdateRefreshTokenRequest dto) {
		
		String token = dto.getRefreshToken();
		RefreshToken refreshToken = getRefreshTokenByToken(token);
		User owner = refreshToken.getOwner();
		
		if (refreshToken.isExpired()) {
			refreshTokenRepo.delete(refreshToken);
			throw new TokenException("refresh token is already expired. you need relogin");
		}
		refreshTokenRepo.delete(refreshToken);
		
		String newRefreshToken = generateRefreshToken(owner).getToken();
		String newAccessToken = generateJwt(owner.getUsername());
		
		return new RefreshTokenResponse(
				newRefreshToken, 
				newAccessToken);
	}
}
