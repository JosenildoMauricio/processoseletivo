create table diretorio (
    id bigint not null auto_increment,
    diretorio_pai_id bigint,
    nome varchar(155) not null,
    data_cadastro timestamp not null,
    data_atualizacao timestamp not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table arquivo (
    id bigint not null auto_increment,
    nome varchar(155) not null,
    diretorio_id bigint not null,
    data_cadastro timestamp not null,
    data_atualizacao timestamp not null,

    primary key (id),
    constraint fk_arquivo_diretorio foreign key (diretorio_id) references diretorio (id)
) engine=InnoDB default charset=utf8mb4;