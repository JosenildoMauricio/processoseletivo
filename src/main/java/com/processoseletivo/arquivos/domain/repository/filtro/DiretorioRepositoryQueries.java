package com.processoseletivo.arquivos.domain.repository.filtro;

import com.processoseletivo.arquivos.domain.model.Diretorio;

import java.util.List;

public interface DiretorioRepositoryQueries {

	List<Diretorio> filtroPorNome(String nome);
	
	void delete(Long id);

}