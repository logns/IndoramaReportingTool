drop table if exists  assign_task_dailylog;
CREATE TABLE IF NOT EXISTS `indorama_poly`.`assign_task_dailylog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `assign_task_id` INT(11) NOT NULL,
  `dailylog_id` INT(11) NOT NULL DEFAULT '-1',
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `dailylog_idfk_` (`dailylog_id` ASC),
  INDEX `assign_task_id_fk_idx` (`assign_task_id` ASC),
  CONSTRAINT `dailylog_bu_ibfk_10`
    FOREIGN KEY (`dailylog_id`)
    REFERENCES `indorama_poly`.`dailylog` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `assign_task_id_fk`
    FOREIGN KEY (`assign_task_id`)
    REFERENCES `indorama_poly`.`assign_task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
