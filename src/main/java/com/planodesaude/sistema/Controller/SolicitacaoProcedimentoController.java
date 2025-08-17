package com.planodesaude.sistema.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.planodesaude.sistema.model.SolicitacaoProcedimento;
import com.planodesaude.sistema.service.SolicitacaoProcedimentoService;

public class SolicitacaoProcedimentoController {

    private final SolicitacaoProcedimentoService service;

    public SolicitacaoProcedimentoController(SolicitacaoProcedimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<SolicitacaoProcedimento> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public SolicitacaoProcedimento solicitar(@RequestBody SolicitacaoProcedimento solicitacao) {
        return service.solicitar(solicitacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }

}
