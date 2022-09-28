TRUNCATE TABLE users;

ALTER TABLE users 
	ADD COLUMN password varchar(255) NOT NULL;
ALTER TABLE users 
	ADD COLUMN email varchar(255) UNIQUE NOT NULL;

CREATE TABLE user_roles (
	user_id 	bigint NOT NULL,
	role 		varchar(10) NOT NULL
);

ALTER TABLE user_roles 
	ADD CONSTRAINT fk_user_roles_user_id_users_user_id 
        FOREIGN KEY (user_id) REFERENCES users (user_id);
