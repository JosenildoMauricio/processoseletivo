package com.processoseletivo.arquivos.domain.repository.filtro;

import com.processoseletivo.arquivos.domain.model.Arquivo;

import java.util.List;

public interface ArquivoRepositoryQueries {

	List<Arquivo> filtroPorNomeArquivo(String nomeArquivo);

	List<Arquivo> filtroPorDiretorio(Long nomeDiretorio);

	void delete(Long id);

}