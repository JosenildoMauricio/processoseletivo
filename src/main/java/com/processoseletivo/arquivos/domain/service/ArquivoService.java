package com.processoseletivo.arquivos.domain.service;

import com.processoseletivo.arquivos.domain.exception.EntidadeEmUsoException;
import com.processoseletivo.arquivos.domain.exception.EntidadeNaoEncontradaException;
import com.processoseletivo.arquivos.domain.model.Arquivo;
import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	public List<Arquivo> listarTodosPorDiretorio(Diretorio diretorio) {
		return arquivoRepository.findByDiretorio(diretorio);
	}

	public List<Arquivo> listarPorNome(String nome) {
		return arquivoRepository.filtroPorNomeArquivo(nome);
	}

	public Arquivo buscarPorId(Long arquivoId) {
		return arquivoRepository.findById(arquivoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de Arquivo com ID %d", arquivoId)));
	}
	
	@Transactional
	public Arquivo criar(Arquivo arquivo) {
		arquivo.setNome(arquivo.getNome());
		
		return arquivoRepository.save(arquivo);
	}

	@Transactional
	public Arquivo renomear(Arquivo arquivo) {

		return arquivoRepository.save(arquivo);
	}
	
	@Transactional
	public void deletar(Arquivo arquivo) {
		try {
			arquivoRepository.delete(arquivo.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Arquivo com ID %d", arquivo.getId()));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("O Arquivo de ID %d não pode ser removido, pois está em uso.", arquivo.getId()));
		}
		
	}

}
