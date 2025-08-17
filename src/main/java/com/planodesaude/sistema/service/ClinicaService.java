package com.planodesaude.sistema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.planodesaude.sistema.model.Clinica;
import com.planodesaude.sistema.repository.ClinicaRepository;

@Service
public class ClinicaService {

    private final ClinicaRepository repository;

    public ClinicaService(ClinicaRepository repository) {
        this.repository = repository;
    }

    public List<Clinica> listarTodos() {
        return repository.findAll();
    }

    public Clinica salvar(Clinica clinica) {
        return repository.save(clinica);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
