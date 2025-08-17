package com.planodesaude.sistema.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planodesaude.sistema.model.Procedimento;
import com.planodesaude.sistema.repository.ProcedimentoRepository;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoRepository repository;

    public ProcedimentoController(ProcedimentoRepository repository) {
        this.repository = repository;
    }

    // Listar todos
    @GetMapping
    public List<Procedimento> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Procedimento buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));
    }

    // Criar procedimento
    @PostMapping
    public Procedimento salvar(@RequestBody Procedimento procedimento) {
        return repository.save(procedimento);
    }

    // Atualizar procedimento
    @PutMapping("/{id}")
    public Procedimento atualizar(@PathVariable Long id, @RequestBody Procedimento novoProcedimento) {
        return repository.findById(id)
                .map(p -> {
                    p.setCodigoTUSS(novoProcedimento.getCodigoTUSS());
                    p.setNome(novoProcedimento.getNome());
                    p.setPreco(novoProcedimento.getPreco());
                    p.setDiasCarencia(novoProcedimento.getDiasCarencia());
                    p.setQuantidadeMaxima(novoProcedimento.getQuantidadeMaxima());
                    p.setSexoPermitido(novoProcedimento.getSexoPermitido());
                    p.setAutorizacaoAutomatica(novoProcedimento.getAutorizacaoAutomatica());
                    p.setPeriodoQtd(novoProcedimento.getPeriodoQtd());
                    return repository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));
    }

    // Deletar procedimento
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
