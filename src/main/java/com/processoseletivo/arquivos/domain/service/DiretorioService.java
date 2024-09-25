package com.processoseletivo.arquivos.domain.service;

import com.processoseletivo.arquivos.domain.exception.EntidadeEmUsoException;
import com.processoseletivo.arquivos.domain.exception.EntidadeNaoEncontradaException;
import com.processoseletivo.arquivos.domain.exception.NegocioException;
import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.repository.DiretorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorioService {
	
	@Autowired
	private DiretorioRepository diretorioRepository;

	public static final String DIRETORIO_RAIZ = "DIRETORIO RAIZ";

	public Diretorio diretorioRaiz() {

		Optional<Diretorio> diretorio = Optional.of(diretorioRepository.findByNome(DIRETORIO_RAIZ).orElseThrow(
				() -> new EntidadeNaoEncontradaException("O Diretório raiz não foi encontrado.")
		));

		return diretorio.get();
	}
	
	public List<Diretorio> listarTodos() {

		List<Diretorio> diretorios = diretorioRepository.findAll();

		return diretorios.stream()
				.filter(d -> d.getDiretorioPai() == null)
				.toList();
	}

	public List<Diretorio> filtroPorNome(String nome) {
		return diretorioRepository.filtroPorNome(nome);
	}

	public Diretorio buscarPorId(Long id) {
		return diretorioRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de Diretorio com ID %d", id)));
	}

	@Transactional
	public Diretorio criar(Diretorio diretorio) {
		try {
			return diretorioRepository.save(diretorio);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(
					String.format("Já existe um diretório com este nome.", diretorio.getNome()));
		}
	}

	@Transactional
	public Diretorio alterar(Diretorio diretorio) {
		return diretorioRepository.save(diretorio);
	}
	
	@Transactional
	public void deletar(Diretorio diretorio) {
		try {
			diretorioRepository.delete(diretorio.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Diretorio com ID %d", diretorio.getId()));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("O Diretorio de ID %d não pode ser removido, pois está em uso.", diretorio.getId()));
		}
		
	}

}
