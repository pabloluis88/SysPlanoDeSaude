package com.planodesaude.sistema.repository;

import  org.springframework.data.jpa.repository.JpaRepository;

import com.planodesaude.sistema.model.ProdutoSaude;

public interface ProdutoSaudeRepository 
    extends JpaRepository<ProdutoSaude, Long> {

    }