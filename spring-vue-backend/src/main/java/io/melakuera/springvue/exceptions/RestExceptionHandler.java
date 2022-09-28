package io.melakuera.springvue.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Map<String, Object>> handleTokenExpired(
			HttpServletRequest req, Exception ex) {
		
		Map<String, Object> body = new HashMap<>();
		String instant = Instant.now().toString();
		String path = req.getRequestURI();
		String error = ex.getMessage();
		int status = HttpStatus.UNAUTHORIZED.value();
		
		body.put("timestamp", instant);
		body.put("status", status);
		body.put("error", error);
		body.put("path", path);
		
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	
}
