alter table pacientes add ativo tinyint;
update pacientes set ativo =1;

ALTER TABLE `vollmed_api`.`pacientes`
CHANGE COLUMN `ativo` `ativo` TINYINT NOT NULL ;