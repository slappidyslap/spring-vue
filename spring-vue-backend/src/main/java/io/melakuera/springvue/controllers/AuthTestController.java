package io.melakuera.springvue.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * Рест контроллер для тестов
 *
 */
@RestController
@RequestMapping("/api/test-auth")
@RequiredArgsConstructor
public class AuthTestController {

	@GetMapping("/admin")
	String admin() {
		return "admin";
	}
	
	@GetMapping("/user")
	String user() {
		return "user";
	}
	
	@GetMapping("/anon")
	String anon() {
		return "anon";
	}
}
