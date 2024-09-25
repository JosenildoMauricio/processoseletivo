set foreign_key_checks = 0;

delete from arquivo;
delete from diretorio;

set foreign_key_checks = 1;

alter table arquivo auto_increment = 1;
alter table diretorio auto_increment = 1;

insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (null, 'DIRETORIO RAIZ', utc_timestamp, utc_timestamp);


