package com.planodesaude.sistema.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planodesaude.sistema.model.ProdutoSaude;
import com.planodesaude.sistema.repository.ProdutoSaudeRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoSaudeController {

    private final ProdutoSaudeRepository repository;

    public ProdutoSaudeController(ProdutoSaudeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProdutoSaude> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ProdutoSaude salvar(@RequestBody ProdutoSaude produto) {
        return repository.save(produto);
    }
}