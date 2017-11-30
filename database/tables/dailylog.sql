drop table dailylog;

CREATE TABLE IF NOT EXISTS `indorama_poly`.`dailylog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
	`assign_task_id` INT(11) NOT NULL DEFAULT -1 ,
  `target_date` VARCHAR(64) NOT NULL DEFAULT '',
  `shift` VARCHAR(64) NOT NULL DEFAULT '',
  `machine` VARCHAR(64) NOT NULL DEFAULT '',
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `timefrom` VARCHAR(20) NOT NULL,
  `timeto` VARCHAR(20) NOT NULL,
  `spare_parts` VARCHAR(64) NOT NULL DEFAULT '',
  `attendby` VARCHAR(64) NOT NULL DEFAULT '',
  `jobtype` VARCHAR(64) NOT NULL DEFAULT '',
  `recordtype` VARCHAR(64) NOT NULL DEFAULT '',
  `status` VARCHAR(64) NOT NULL DEFAULT '',
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `done_percentage` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `assign_task_id_index` (`assign_task_id` ASC),
  INDEX `dailylog_jobtype` (`jobtype` ASC),
  INDEX `dailylog_status` (`status` ASC),
  INDEX `dailylog_attendby` (`attendby` ASC),
  CONSTRAINT `fk_assigntask_id`
  FOREIGN KEY (`assign_task_id`)
  REFERENCES `indorama_poly`.`assign_task` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `indorama_poly`.`dailylog` 
CHANGE COLUMN `attendby` `attendby` VARCHAR(275) NOT NULL DEFAULT '' ;

ALTER TABLE dailylog ADD UNIQUE dlat_id(id, assign_task_id);
SHOW INDEXES FROM dailylog;
