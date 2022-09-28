package io.melakuera.springvue.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table(name = "refresh_tokens")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "refresh_token_id")
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;
	
	@Column(name = "token", unique = true, nullable = false)
	private String token;
	
	@Column(name = "expiry_date", nullable = false)
	private Instant expiryDate;

	public RefreshToken(User owner, String tokenValue, Instant expiryDate) {
		super();
		this.owner = owner;
		this.token = tokenValue;
		this.expiryDate = expiryDate;
	}
	
	public boolean isExpired() {
		return Instant.now().isAfter(this.getExpiryDate());
	}
}
