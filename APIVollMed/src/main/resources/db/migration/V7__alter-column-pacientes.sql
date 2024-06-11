ALTER TABLE `vollmed_api`.`pacientes`
CHANGE COLUMN `telefone` `telefone` VARCHAR(20) NOT NULL AFTER `cpf`;