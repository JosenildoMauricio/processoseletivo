package com.processoseletivo.arquivos.infrastructure.repository;

import com.processoseletivo.arquivos.domain.model.Arquivo;
import com.processoseletivo.arquivos.domain.repository.filtro.ArquivoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Repository
public class ArquivoRepositoryImpl implements ArquivoRepositoryQueries {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public List<Arquivo> filtroPorNomeArquivo(String nomeArquivo) {

		var jpql = new StringBuilder();
		jpql.append("from Arquivo where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if(StringUtils.hasLength(nomeArquivo)) {
			jpql.append("and nome like :nomeArquivo ");
			parametros.put("nomeArquivo", "%" + nomeArquivo + "%");
		}

		TypedQuery<Arquivo> query = manager.createQuery(jpql.toString(), Arquivo.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}

	@Override
	public List<Arquivo> filtroPorDiretorio(Long diretorioId) {

		var jpql = new StringBuilder();
		jpql.append("from Arquivo where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if(diretorioId != null) {
			jpql.append("and diretorio.id = :diretorioId ");
			parametros.put("diretorioId", diretorioId );
		}

		TypedQuery<Arquivo> query = manager.createQuery(jpql.toString(), Arquivo.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}
    
    @Transactional
	@Override
	public void delete(Long id) {
    	Arquivo arquivo = manager.find(Arquivo.class, id);
		
		if (arquivo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(arquivo);
	}

}
