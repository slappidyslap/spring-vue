package io.melakuera.springvue.services;

import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import io.melakuera.springvue.Constants;
import io.melakuera.springvue.exceptions.UserPicServeException;
import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPicStorage {
	
	private final UserService userService;
	private final UserRepo userRepo;

	@PostConstruct
	public void init() {
		try {
			Files.createDirectory(Constants.root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@PreDestroy
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(Constants.root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transferFile(MultipartFile file, String filename) {
		try {
			file.transferTo(Constants.root.resolve(filename));
		} catch (IllegalStateException | IOException e) {
			throw new UserPicServeException("failed to save file to directory");
		}
	}

	public void serveUserPic(MultipartFile file, String username) {
		
		String uniqueFilename = getUniqueFilename(file);
		
		String userPicUrl = createUserPicUrl(uniqueFilename);

		putUserPicUrl(username, userPicUrl);

		transferFile(file, uniqueFilename);
	}

	private void putUserPicUrl(String username, String userPicUrl) {
		
		User foundUser = userService.getByUsername(username);
		foundUser.setUserPicUrl(userPicUrl);
		userRepo.save(foundUser);
	}
	
	private String getUniqueFilename(MultipartFile file) {
		
		String fileOriginalFilename = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		
		return uuid + "_" + fileOriginalFilename;
	}
	
	private String createUserPicUrl(String uniqueFilename) {
		
		return Constants.BASE_URL + "/" + uniqueFilename;
	}
}
