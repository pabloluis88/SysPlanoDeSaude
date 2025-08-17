package com.planodesaude.sistema.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.planodesaude.sistema.model.Beneficiario;
import com.planodesaude.sistema.model.Procedimento;
import com.planodesaude.sistema.model.SolicitacaoProcedimento;
import com.planodesaude.sistema.repository.SolicitacaoProcedimentoRepository;
import com.planodesaude.sistema.repository.BeneficiarioRepository;

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
    Beneficiario beneficiario = beneficiarioRepository.findById(
            solicitacao.getBeneficiario().getId())
        .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));

    Procedimento procedimento = procedimentoRepository.findById(
            solicitacao.getProcedimento().getId())
        .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

    solicitacao.setBeneficiario(beneficiario);
    solicitacao.setProcedimento(procedimento);

    // Regra de carência
    long diasDesdeInclusao = ChronoUnit.DAYS.between(
            beneficiario.getDataInclusao(),
            solicitacao.getDataSolicitacao()
    );

    if (diasDesdeInclusao < procedimento.getDiasCarencia()) {
        solicitacao.setStatus("AUDITORIA");
        solicitacao.setObservacao("Carência não cumprida: " + diasDesdeInclusao + " dias");
    } else if (procedimento.getAutorizacaoAutomatica()) {
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
