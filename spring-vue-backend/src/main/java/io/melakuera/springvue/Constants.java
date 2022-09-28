package io.melakuera.springvue;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
	
	public static final Path root = Paths.get("uploads");
	public static final String BASE_URL = "http://localhost:8080/uploads";
	public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?'{|} ~^.-]+@[a-zA-Z0-9.-]+$";

	private Constants() {}
}
