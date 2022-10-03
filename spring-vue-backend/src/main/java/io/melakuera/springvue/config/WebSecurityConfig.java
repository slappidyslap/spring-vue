package io.melakuera.springvue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.melakuera.springvue.security.JwtAuthEntryPoint;
import io.melakuera.springvue.security.JwtFilter;
import io.melakuera.springvue.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
		
		return http
				.cors()
				.and()
				.csrf().disable()
				.headers().frameOptions().disable()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
				.and()
				.authorizeHttpRequests()
					.antMatchers("/api/auth/**").permitAll()
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/uploads/**").permitAll()
					.antMatchers("/api/test-auth/admin").hasAuthority("ROLE_ADMIN")
					.antMatchers("/api/test-auth/user").hasAuthority("ROLE_USER")
					.antMatchers("/api/test-auth/anon").permitAll()
					.antMatchers("/api/users/*").permitAll()
					.antMatchers("/ws/**").permitAll()
				.anyRequest()
					.authenticated()
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}



	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
}