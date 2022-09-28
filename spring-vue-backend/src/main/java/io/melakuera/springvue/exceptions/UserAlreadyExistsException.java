package io.melakuera.springvue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8123267464680008894L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
