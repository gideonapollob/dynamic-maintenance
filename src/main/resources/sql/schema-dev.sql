CREATE TABLE `dynamic_maintenance`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `created_by` INT UNSIGNED NOT NULL,
  `created_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

ALTER TABLE `dynamic_maintenance`.`user`
ADD INDEX `user_user_fk_idx` (`created_by` ASC) VISIBLE;
;

ALTER TABLE `dynamic_maintenance`.`user`
ADD CONSTRAINT `user_user_fk`
  FOREIGN KEY (`created_by`)
  REFERENCES `dynamic_maintenance`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `dynamic_maintenance`.`user`(`username`, `created_by`, `created_date`)
values('administrator', 1, NOW());

CREATE TABLE `dynamic_maintenance`.`password` (
  `user_id` INT UNSIGNED NOT NULL,
  `password_hash` VARCHAR(60) NOT NULL,
  `updated_by` INT UNSIGNED NOT NULL,
  `updated_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`user_id`, `updated_by`),
  INDEX `password_user_fk_idx` (`updated_by` ASC) VISIBLE,
  CONSTRAINT `password_user_fk`
    FOREIGN KEY (`updated_by`)
    REFERENCES `dynamic_maintenance`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
