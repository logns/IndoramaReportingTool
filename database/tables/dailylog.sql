CREATE TABLE IF NOT EXISTS `indorama_poly`.`dailylog` (
  `id` INT(11) NOT NULL,
  `assign_task_title` VARCHAR(150) NOT NULL DEFAULT '',
  `target_date` VARCHAR(64) NOT NULL DEFAULT '',
  `shift` VARCHAR(64) NOT NULL DEFAULT '',
  `machine` VARCHAR(64) NOT NULL DEFAULT '',
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `timefrom` TIME(6) NOT NULL,
  `timeto` TIME(6) NOT NULL,
  `spare_parts` VARCHAR(64) NOT NULL DEFAULT '',
  `attendby` VARCHAR(64) NOT NULL DEFAULT '',
  `jobtype` VARCHAR(64) NOT NULL DEFAULT '',
  `recordtype` VARCHAR(64) NOT NULL DEFAULT '',
  `status` VARCHAR(64) NOT NULL DEFAULT '',
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `done_percentage` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `dailylog_jobtype` (`jobtype` ASC),
  INDEX `dailylog_status` (`status` ASC),
  INDEX `dailylog_attendby` (`attendby` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `indorama_poly`.`dailylog` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;
