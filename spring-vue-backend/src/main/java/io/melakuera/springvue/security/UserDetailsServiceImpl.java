package io.melakuera.springvue.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.melakuera.springvue.models.User;
import io.melakuera.springvue.repositories.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepo userRepo;

	public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?'{|} ~^.-]+@[a-zA-Z0-9.-]+$";


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		if (login.matches(EMAIL_VALIDATION_REGEX)) {
			User foundUser = userRepo.findByEmail(login).orElseThrow(() -> {
				throw new UsernameNotFoundException("User not found with email: " + login);
			});
			
			return UserDetailsImpl.build(foundUser);
			
		} else {
			User foundUser = userRepo.findByUsername(login).orElseThrow(() -> {
				throw new UsernameNotFoundException("User not found with username: " + login);
			});
			
			return UserDetailsImpl.build(foundUser);
		}
	}
}
