DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id			bigint AUTO_INCREMENT PRIMARY KEY,
	fullname 		varchar(255) NOT NULL,
	username 		varchar(255) NOT NULL,
	user_pic_url 	text NOT NULL
);

INSERT INTO users (fullname, username, user_pic_url) 
	VALUES ('Eld Musabaev', 'slappidyslap', 'http://localhost:8080/uploads/20213ad3fdb0b6ab88ddd5397c262522.jpg');
INSERT INTO users (fullname, username, user_pic_url) 
	VALUES ('Nurai Temiraliev', 'naraomur', 'http://localhost:8080/uploads/images.jpg');
INSERT INTO users (fullname, username, user_pic_url) 
	VALUES ('balancha', 'balanchiev', 'http://localhost:8080/uploads/default-ava.png');