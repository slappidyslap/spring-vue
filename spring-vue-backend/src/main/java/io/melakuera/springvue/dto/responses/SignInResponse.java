package io.melakuera.springvue.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponse {

	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	private long userId;
	private String email;
	private String username;
	private String[] roles;
	
	public SignInResponse(
			String accessToken, 
			String refreshToken, 
			long userId, 
			String email, 
			String username,
			String[] roles) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.roles = roles;
	}
}
