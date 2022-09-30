package io.melakuera.springvue.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.melakuera.springvue.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "user_roles", 
			joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;

	public User(
			String username, 
			String password,
			String email,
			Teacher teacher,
			Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.teacher = teacher;
		this.roles = roles;
	}
}
