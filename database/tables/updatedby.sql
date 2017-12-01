drop table updatedby;

CREATE TABLE `updatedby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `updaterby_realname` varchar(64) NOT NULL,
  `updated_time` varchar(45) NOT NULL,
    `assign_task_id` INT(11) NOT NULL DEFAULT -1 ,
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `ub_assign_task_id_index` (`assign_task_id` ASC), CONSTRAINT `fk_ub_assigntask_id`
  FOREIGN KEY (`assign_task_id`)
  REFERENCES `indorama_poly`.`assign_task` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
