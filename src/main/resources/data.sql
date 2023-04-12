DROP TABLE IF EXISTS user_register;
 
CREATE TABLE user_register (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  date_birth DATE NOT NULL,
  country_residence VARCHAR(50) NOT NULL,
  phone_number VARCHAR(20),
  gender ENUM('MALE', 'FEMALE', 'NON_BINARY')
);
 
INSERT INTO user_register (name, date_birth, country_residence, phone_number, gender) VALUES
  ('Albert33', '1979-12-12', 'France', '+33625865668', 'MALE'),
  
 ('Caroline54', '1959-10-01', 'France', '+33678540940', 'FEMALE'),
 ('LepetitGrand', '1992-03-21', 'France', NULL, 'NON_BINARY'),
('InconnuBordeaux', '1945-12-12', 'France', NULL, NULL);
  
 