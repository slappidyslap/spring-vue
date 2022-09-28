package io.melakuera.springvue.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.melakuera.springvue.exceptions.UserNotFoundException;
import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;
	private final UserPicStorage userPicStorage;
	
	public User getByUsername(String username) {
		return userRepo.findByUsername(username).orElseThrow(() -> {
			throw new UserNotFoundException(
					"such user by username (%s) not found", username);
		});
	}
	
	public User getById(long userId) {
		return userRepo.findById(userId).orElseThrow(() -> {
			throw new UserNotFoundException(
					"such user by id (%s) not found", userId);
		});
	}

	public ResponseEntity<String> updateUserPic(
			MultipartFile file, 
			String username) {

		userPicStorage.serveUserPic(file, username);

		return ResponseEntity.ok("success");
	}
}
