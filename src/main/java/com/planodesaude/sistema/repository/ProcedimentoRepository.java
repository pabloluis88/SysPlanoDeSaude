package com.planodesaude.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planodesaude.sistema.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{

}
