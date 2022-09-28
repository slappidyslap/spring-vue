package io.melakuera.springvue;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.UserRepo;
import io.melakuera.springvue.security.Role;
import io.melakuera.springvue.services.UserPicStorage;

@SpringBootApplication
@Configuration
public class SpringVueApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringVueApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(
			UserPicStorage userPicStorage,
			UserRepo userRepo,
			BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			
			userRepo.save(new User("eld", "eld mus", passwordEncoder.encode("1"), "eld@mail.com", Set.of(Role.ROLE_ADMIN, Role.ROLE_USER)));
			userRepo.save(new User("tom", "tom tom", passwordEncoder.encode("1"), "tom@mail.com", Set.of(Role.ROLE_USER)));
			Path root = Paths.get("C:\\Users\\musab\\Pictures");
			
			File path1 = root.resolve("20213ad3fdb0b6ab88ddd5397c262522.jpg").toFile();
			MultipartFile image1 = new MockMultipartFile(path1.getName(), new FileInputStream(path1));
			
			userPicStorage.transferFile(image1, "20213ad3fdb0b6ab88ddd5397c262522.jpg");

			File path2 = root.resolve("images.jpg").toFile();
			MultipartFile image2 = new MockMultipartFile(path2.getName(), new FileInputStream(path2));
			
			userPicStorage.transferFile(image2, "images.jpg");
			
			File path3 = root.resolve("default-ava.png").toFile();
			MultipartFile defaultUserPic = new MockMultipartFile(path3.getName(), new FileInputStream(path3));
			
			userPicStorage.transferFile(defaultUserPic, "default-ava.png");
		};
	}
}
