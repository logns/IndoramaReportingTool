
DROP TABLE `indorama_poly`.`assign_task`;

CREATE TABLE IF NOT EXISTS `indorama_poly`.`assign_task` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NOT NULL,
  `assigned_to` VARCHAR(245) NOT NULL,
  `priority` VARCHAR(45) NOT NULL,
  `target_date` VARCHAR(45) NOT NULL,
  `done_percentage` VARCHAR(45) NOT NULL,
  `last_edit` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `indorama_poly`.`assign_task` 
ADD UNIQUE INDEX `assign_task_title_index` (`title` ASC);