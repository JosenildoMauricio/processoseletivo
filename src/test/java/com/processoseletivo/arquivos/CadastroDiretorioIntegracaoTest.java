package com.processoseletivo.arquivos;

import com.processoseletivo.arquivos.domain.model.Diretorio;
import com.processoseletivo.arquivos.domain.service.DiretorioService;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
class CadastroDiretorioIntegracaoTest {

    @Autowired
    private DiretorioService diretorioService;

    @Test
    void deveCadastrarUmDiretorioFilhoComSucesso() {
        Diretorio diretorioPai = new Diretorio();
        diretorioPai.setId(1L);

        Diretorio novoDiretorio = new Diretorio();
        novoDiretorio.setDiretorioPai(diretorioPai);
        novoDiretorio.setNome("Novo diretorio teste");

        novoDiretorio = diretorioService.criar(novoDiretorio);

        assertThat(novoDiretorio.getId()).isNotNull();
    }

}