drop table dailylog_bu;

CREATE TABLE IF NOT EXISTS `indorama_poly`.`dailylog_bu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bu_id` INT(11) NOT NULL,
  `dailylog_id` INT(11)  NOT NULL DEFAULT '-1',
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `dailylog_id` (`dailylog_id` ASC),
  INDEX `dailog_bu_bu_idfk_1_idx` (`bu_id` ASC),
  CONSTRAINT `dailylog_bu_ibfk_1`
    FOREIGN KEY (`dailylog_id`)
    REFERENCES `indorama_poly`.`dailylog` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `dailog_bu_bu_idfk_1`
    FOREIGN KEY (`bu_id`)
    REFERENCES `indorama_poly`.`bu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `indorama_poly`.`dailylog_bu` 
ADD UNIQUE INDEX `dailylog_id_UNIQUE` (`dailylog_id` ASC);

ALTER TABLE `indorama_poly`.`dailylog_bu` 
DROP FOREIGN KEY `dailog_bu_bu_idfk_1`;
ALTER TABLE `indorama_poly`.`dailylog_bu` 
CHANGE COLUMN `bu_id` `bu_id` INT(11) NOT NULL DEFAULT '-1' ;
ALTER TABLE `indorama_poly`.`dailylog_bu` 
ADD CONSTRAINT `dailog_bu_bu_idfk_1`
  FOREIGN KEY (`bu_id`)
  REFERENCES `indorama_poly`.`bu` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `indorama_poly`.`dailylog_bu` 
DROP FOREIGN KEY `dailog_bu_bu_idfk_1`;
ALTER TABLE `indorama_poly`.`dailylog_bu` 
ADD CONSTRAINT `dailog_bu_bu_idfk_1`
  FOREIGN KEY (`bu_id`)
  REFERENCES `indorama_poly`.`bu` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
