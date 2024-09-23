package com.processoseletivo.arquivos.api.controller;

import com.processoseletivo.arquivos.domain.model.Arquivo;
import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.service.ArquivoService;
import com.processoseletivo.arquivos.domain.service.DiretorioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arquivos")
public class ArquivoController {

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private DiretorioService diretorioService;

	@GetMapping("/diretorio/{diretorioId}")
	public ResponseEntity<List<Arquivo>> listarTodosPorDiretorio(@PathVariable("diretorioId") Long diretorioId) {
		Diretorio diretorio = diretorioService.buscarPorId(diretorioId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(arquivoService.listarTodosPorDiretorio(diretorio));
	}

	@GetMapping("/filtro-nome")
	public ResponseEntity<List<Arquivo>> filtroNome(@RequestParam("nome") String nome) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(arquivoService.listarPorNome(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Arquivo> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(arquivoService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Arquivo> criar(@RequestBody @Valid Arquivo arquivo) {
		Diretorio diretorio = diretorioService.buscarPorId(arquivo.getDiretorio().getId());
		arquivo.setDiretorio(diretorio);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(arquivoService.criar(arquivo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Arquivo> alterar(@PathVariable("id") Long id, @RequestBody @Valid Arquivo arquivo) {
		Arquivo arquivoAtual = arquivoService.buscarPorId(id);

		BeanUtils.copyProperties(arquivo, arquivoAtual, "id", "diretorio", "contentType", "dataCadastro", "dataAtualizacao");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(arquivoService.renomear(arquivoAtual));

	}

	@DeleteMapping("/{diretorioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("diretorioId") Long diretorioId) {
		Arquivo arquivo = arquivoService.buscarPorId(diretorioId);

		arquivoService.deletar(arquivo);
	}
}
