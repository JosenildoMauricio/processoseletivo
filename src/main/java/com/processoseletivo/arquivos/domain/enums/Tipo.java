package com.processoseletivo.arquivos.domain.enums;

import lombok.Getter;

@Getter
public enum Tipo {

	DIRETORIO("DIRETORIO"),
	ARQUIVO("ARQUIVO");

	private String descricao;

	private Tipo(String descricao) {
		this.descricao = descricao;
	}

}
