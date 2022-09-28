CREATE TABLE refresh_tokens (
    refresh_token_id bigint AUTO_INCREMENT PRIMARY KEY,
    expiry_date timestamp NOT NULL,
    token varchar(255) NOT NULL,
    user_id bigint NOT NULL
);

ALTER TABLE refresh_tokens 
	ADD CONSTRAINT fk_refresh_tokens_user_id_users_user_id 
        FOREIGN KEY (user_id) REFERENCES users (user_id);