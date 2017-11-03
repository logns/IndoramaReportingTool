DROP TABLE if exists `indorama_poly`.`assign_tickets`;
CREATE TABLE `indorama_poly`.`assign_tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
        
    #foreign key bu.id	
	dailylog_id integer not null,
	dailylog_title VARCHAR(145) not null,
  `subject` VARCHAR(145) NOT NULL,
  `dates` VARCHAR(45) NOT NULL,
  `shift` VARCHAR(45) NOT NULL,
  `machine` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `timefrom` VARCHAR(45) NOT NULL,
  `timeto` VARCHAR(45) NOT NULL,
  `spareparts` VARCHAR(45) NOT NULL,
  `attendby` VARCHAR(245) NOT NULL,
  `assignedto` VARCHAR(245) NOT NULL,
  `priority` VARCHAR(45) NOT NULL,
  `targetdate` VARCHAR(45) NOT NULL,
  `donepercentage` VARCHAR(45) NOT NULL,
  `status` VARCHAR(145) NOT NULL,
  `comments` VARCHAR(245) NOT NULL,
  
  last_edit timestamp not null default current_timestamp on update current_timestamp,
	
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
ALTER TABLE `indorama_poly`.`assign_tickets` 
ADD INDEX `dailylog_id` (`dailylog_id` ASC);
ALTER TABLE `indorama_poly`.`assign_tickets` 
ADD CONSTRAINT `dailylog_id`
  FOREIGN KEY (`dailylog_id`)
  REFERENCES `indorama_poly`.`dailylog` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
