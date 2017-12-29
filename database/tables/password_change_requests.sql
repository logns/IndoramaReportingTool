DROP TABLE `indorama_poly`.`password_change_requests`;
CREATE TABLE `indorama_poly`.`password_change_requests` (
  `hash_id` VARCHAR(100) NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  `users_id` INT(11) NOT NULL,
  `no_of_attempts` INT NOT NULL,
  UNIQUE INDEX `users_id_UNIQUE` (`users_id` ASC));