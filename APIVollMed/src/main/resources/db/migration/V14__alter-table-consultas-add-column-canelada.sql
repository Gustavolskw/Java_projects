alter table consultas add cancelada tinyint;
update consultas set cancelada =0;
ALTER TABLE `vollmed_api`.`consultas`
CHANGE COLUMN `cancelada` `cancelada` TINYINT NOT NULL ;