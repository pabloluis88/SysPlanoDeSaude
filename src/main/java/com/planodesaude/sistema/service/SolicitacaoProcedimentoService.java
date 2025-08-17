package com.planodesaude.sistema.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.planodesaude.sistema.model.SolicitacaoProcedimento;
import com.planodesaude.sistema.repository.SolicitacaoProcedimentoRepository;

@Service
public class SolicitacaoProcedimentoService {

    private final SolicitacaoProcedimentoRepository repository;

    public SolicitacaoProcedimentoService(SolicitacaoProcedimentoRepository repository) {
        this.repository = repository;
    }

    public List<SolicitacaoProcedimento> listarTodos() {
        return repository.findAll();
    }

    public SolicitacaoProcedimento solicitar(SolicitacaoProcedimento solicitacao) {
        // Regra de carência
        long diasDesdeInclusao = ChronoUnit.DAYS.between(
                solicitacao.getBeneficiario().getDataInclusao(),
                solicitacao.getDataSolicitacao()
        );

        if (diasDesdeInclusao < solicitacao.getProcedimento().getDiasCarencia()) {
            solicitacao.setStatus("AUDITORIA");
            solicitacao.setObservacao("Carência não cumprida: " + diasDesdeInclusao + " dias");
        } else if (solicitacao.getProcedimento().getAutorizacaoAutomatica()) {
            solicitacao.setStatus("AUTORIZADO");
        } else {
            solicitacao.setStatus("AUDITORIA");
        }

        return repository.save(solicitacao);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
