package io.melakuera.springvue.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
	
	private String login;
	private String password;
}
