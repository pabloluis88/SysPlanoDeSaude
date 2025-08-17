package com.planodesaude.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planodesaude.sistema.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
