package io.melakuera.springvue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 8013678245024578444L;

	public TokenException(String message, Object... args) {
		super(String.format(message, args));
	}
}
