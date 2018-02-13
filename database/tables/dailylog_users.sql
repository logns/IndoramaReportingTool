drop table if exists dailylog_users;

CREATE TABLE IF NOT EXISTS `indorama_poly`.`dailylog_users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `users_id` INT(11) NOT NULL,
  `dailylog_id` INT(11) NOT NULL DEFAULT '-1',
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `dailylog_id` (`dailylog_id` ASC),
  INDEX `users_id` (`users_id` ASC),
  CONSTRAINT `dailylog_users_ibfk_2`
    FOREIGN KEY (`users_id`)
    REFERENCES `indorama_poly`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
