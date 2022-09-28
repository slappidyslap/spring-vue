package io.melakuera.springvue.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class RestApiExceptionHandler {
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Map<String, Object>> handleTokenExpired(
			HttpServletRequest request, Exception exception) {

		return buildBody(exception, request, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<Map<String, Object>> handleUploadMultipart(
			HttpServletRequest request, Exception exception) {

		return buildBody(exception, request, HttpStatus.PAYLOAD_TOO_LARGE);
	}
	
	private ResponseEntity<Map<String, Object>> buildBody(
			final Exception exception,
			final HttpServletRequest request,
			final HttpStatus httpStatus) {

		Map<String, Object> body = new HashMap<>();

		body.put("timestamp", Instant.now().toString());
		body.put("status", httpStatus.value());
		body.put("message", exception.getMessage());
		body.put("error", httpStatus.getReasonPhrase());
		body.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(body, httpStatus);
	}
}
