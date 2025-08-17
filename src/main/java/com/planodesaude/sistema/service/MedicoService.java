package com.planodesaude.sistema.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.planodesaude.sistema.model.Medico;
import com.planodesaude.sistema.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    public Medico salvar(Medico medico) {
        return repository.save(medico);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

