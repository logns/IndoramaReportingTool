CREATE TABLE `updatedby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `updaterby_realname` varchar(64) NOT NULL,
  `updated_time` varchar(45) NOT NULL,
  `last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
