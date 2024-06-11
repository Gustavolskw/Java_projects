CREATE TABLE `aluraplay`.`videos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL,
  `descricao` VARCHAR(90) NULL,
  `url` VARCHAR(350) NULL,
  `imagem` VARCHAR(500) NULL,
  PRIMARY KEY (`id`));