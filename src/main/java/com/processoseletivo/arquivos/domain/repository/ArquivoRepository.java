package com.processoseletivo.arquivos.domain.repository;

import com.processoseletivo.arquivos.domain.model.Arquivo;
import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.filtro.ArquivoRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>, ArquivoRepositoryQueries {

    List<Arquivo> findByDiretorio(Diretorio diretorio);
}
