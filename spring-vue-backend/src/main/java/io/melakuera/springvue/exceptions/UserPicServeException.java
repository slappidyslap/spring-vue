package io.melakuera.springvue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserPicServeException extends RuntimeException {

	private static final long serialVersionUID = 7987494341315213887L;

	public UserPicServeException(String message) {
		super(message);
	}
}
