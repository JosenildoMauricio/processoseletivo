set foreign_key_checks = 0;

delete from arquivo;
delete from diretorio;

set foreign_key_checks = 1;

alter table arquivo auto_increment = 1;
alter table diretorio auto_increment = 1;

insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (null, 'DIRETORIO RAIZ', utc_timestamp, utc_timestamp);
	insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('arquivo da raiz.pdf', 1, utc_timestamp, utc_timestamp);
	insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('arquivo da raiz texto.txt', 1, utc_timestamp, utc_timestamp);

	insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (1, 'Documentos', utc_timestamp, utc_timestamp);
		insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('lista de compras.txt', 2, utc_timestamp, utc_timestamp);

		insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (2, 'Contas a pagar', utc_timestamp, utc_timestamp);
			insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('contas - Janeiro.docx', 3, utc_timestamp, utc_timestamp);

		insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (2, 'Vários documentos', utc_timestamp, utc_timestamp);
			insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('atestado medico.pdf', 4, utc_timestamp, utc_timestamp);
			insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('lista de endereços úteis.txt', 4, utc_timestamp, utc_timestamp);

			insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (4, 'Fotos viagens', utc_timestamp, utc_timestamp);
				insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('001.jpg', 5, utc_timestamp, utc_timestamp);
				insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('002.jpg', 5, utc_timestamp, utc_timestamp);
				insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('08-03-2024.png', 5, utc_timestamp, utc_timestamp);

		insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (2, 'Certidões', utc_timestamp, utc_timestamp);
			insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('certidão de titulo eleitoral.pdf', 6, utc_timestamp, utc_timestamp);

	insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (1, 'Musicas', utc_timestamp, utc_timestamp);
		insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('minha musica.mp3', 7, utc_timestamp, utc_timestamp);
		insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('musica.mp3', 7, utc_timestamp, utc_timestamp);

	insert into diretorio (diretorio_pai_id, nome, data_cadastro, data_atualizacao) values (1, 'Vídeos', utc_timestamp, utc_timestamp);
		insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('um filme.mp4', 8, utc_timestamp, utc_timestamp);
		insert into arquivo (nome, diretorio_id, data_cadastro, data_atualizacao) values ('filme caseiro.mp4', 8, utc_timestamp, utc_timestamp);



