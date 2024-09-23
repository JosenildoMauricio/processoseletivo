package com.processoseletivo.arquivos.api.controller;

import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.service.DiretorioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diretorios")
public class DiretorioController {

	@Autowired
	private DiretorioService diretorioService;

	@GetMapping
	public ResponseEntity<List<Diretorio>> listarTodos() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(diretorioService.listarTodos());
	}

	@GetMapping("/filtro-nome")
	public ResponseEntity<List<Diretorio>> filtroNome(@RequestParam("nome") String nome) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(diretorioService.filtroPorNome(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Diretorio> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(diretorioService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Diretorio> criar(@RequestBody @Valid Diretorio diretorio) {
		if(diretorio.getDiretorioPai() != null) {
			Diretorio diretorioPai = diretorioService.buscarPorId(diretorio.getDiretorioPai().getId());
			Diretorio novoSubDiretorio = new Diretorio();
			novoSubDiretorio.setNome(diretorio.getNome());
			novoSubDiretorio.setDiretorioPai(diretorioPai);

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(diretorioService.criar(novoSubDiretorio));
		} else {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(diretorioService.criar(diretorio));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Diretorio> alterar(@PathVariable("id") Long id, @RequestBody @Valid Diretorio diretorio) {
		Diretorio diretorioAtual = diretorioService.buscarPorId(id);

		BeanUtils.copyProperties(diretorio, diretorioAtual, "id", "diretorios", "dataCadastro", "dataAtualizacao");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(diretorioService.alterar(diretorio));

	}

	@DeleteMapping("/{diretorioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("diretorioId") Long diretorioId) {
		Optional<Diretorio> diretorio = Optional.ofNullable(diretorioService.buscarPorId(diretorioId));

		diretorioService.deletar(diretorio.get());
	}
}
