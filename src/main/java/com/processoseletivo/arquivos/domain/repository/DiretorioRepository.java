package com.processoseletivo.arquivos.domain.repository;

import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.filtro.DiretorioRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiretorioRepository extends JpaRepository<Diretorio, Long>, DiretorioRepositoryQueries {

//    @Query(value = "select d from Diretorio d where d.nome = :nome")
    Optional<Diretorio> findByNome(@Param("nome") String nome);
	
}
