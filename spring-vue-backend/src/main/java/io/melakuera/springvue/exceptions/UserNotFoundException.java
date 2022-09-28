package io.melakuera.springvue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 257712267390766033L;

	public UserNotFoundException(String msg, Object... args) {
		super(String.format(msg, args));
	}
}
