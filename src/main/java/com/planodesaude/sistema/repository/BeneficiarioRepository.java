package com.planodesaude.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planodesaude.sistema.model.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
}