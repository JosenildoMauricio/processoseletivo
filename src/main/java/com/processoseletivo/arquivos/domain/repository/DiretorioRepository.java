package com.processoseletivo.arquivos.domain.repository;

import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.filtro.DiretorioRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorioRepository extends JpaRepository<Diretorio, Long>, DiretorioRepositoryQueries {
	

	
}
