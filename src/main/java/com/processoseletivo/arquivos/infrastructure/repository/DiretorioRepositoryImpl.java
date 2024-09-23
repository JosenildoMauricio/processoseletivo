package com.processoseletivo.arquivos.infrastructure.repository;

import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.filtro.DiretorioRepositoryQueries;
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
public class DiretorioRepositoryImpl implements DiretorioRepositoryQueries {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public List<Diretorio> filtroPorNome(String nome) {

		var jpql = new StringBuilder();
		jpql.append("from Diretorio where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		TypedQuery<Diretorio> query = manager.createQuery(jpql.toString(), Diretorio.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}
    
    @Transactional
	@Override
	public void delete(Long id) {
    	Diretorio diretorio = manager.find(Diretorio.class, id);
		
		if (diretorio == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(diretorio);
	}

}
