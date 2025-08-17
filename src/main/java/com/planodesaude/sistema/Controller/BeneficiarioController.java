package com.planodesaude.sistema.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planodesaude.sistema.model.Beneficiario;
import com.planodesaude.sistema.model.ProdutoSaude;
import com.planodesaude.sistema.repository.BeneficiarioRepository;
import com.planodesaude.sistema.repository.ProdutoSaudeRepository;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioRepository beneficiarioRepository;
    private final ProdutoSaudeRepository produtoSaudeRepository;

    public BeneficiarioController(BeneficiarioRepository beneficiarioRepository,
                                  ProdutoSaudeRepository produtoSaudeRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.produtoSaudeRepository = produtoSaudeRepository;
    }

    // Listar todos
    @GetMapping
    public List<Beneficiario> listar() {
        return beneficiarioRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Optional<Beneficiario> buscarPorId(@PathVariable Long id) {
        return beneficiarioRepository.findById(id);
    }

    // Criar beneficiário (recebe o idProduto no JSON)
    @PostMapping
    public Beneficiario salvar(@RequestBody Beneficiario beneficiario) {
        if (beneficiario.getProdutoSaude() != null && beneficiario.getProdutoSaude().getId() != null) {
            ProdutoSaude produto = produtoSaudeRepository
                    .findById(beneficiario.getProdutoSaude().getId())
                    .orElseThrow(() -> new RuntimeException("Produto de Saúde não encontrado!"));
            beneficiario.setProdutoSaude(produto);
        }
        return beneficiarioRepository.save(beneficiario);
    }

    // Atualizar beneficiário
    @PutMapping("/{id}")
    public Beneficiario atualizar(@PathVariable Long id, @RequestBody Beneficiario novoBeneficiario) {
        return beneficiarioRepository.findById(id)
                .map(b -> {
                    b.setNome(novoBeneficiario.getNome());
                    b.setCpf(novoBeneficiario.getCpf());
                    b.setCidade(novoBeneficiario.getCidade());
                    b.setBairro(novoBeneficiario.getBairro());
                    b.setEstado(novoBeneficiario.getEstado());
                    b.setDataNascimento(novoBeneficiario.getDataNascimento());
                    b.setEstadoCivil(novoBeneficiario.getEstadoCivil());
                    b.setSexo(novoBeneficiario.getSexo());
                    b.setStatus(novoBeneficiario.getStatus());

                    if (novoBeneficiario.getProdutoSaude() != null &&
                        novoBeneficiario.getProdutoSaude().getId() != null) {
                        ProdutoSaude produto = produtoSaudeRepository
                                .findById(novoBeneficiario.getProdutoSaude().getId())
                                .orElseThrow(() -> new RuntimeException("Produto de Saúde não encontrado!"));
                        b.setProdutoSaude(produto);
                    }

                    return beneficiarioRepository.save(b);
                })
                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado!"));
    }

    // Deletar beneficiário
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        beneficiarioRepository.deleteById(id);
    }
}
