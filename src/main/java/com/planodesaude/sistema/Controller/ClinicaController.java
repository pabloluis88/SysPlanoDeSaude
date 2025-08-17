package com.planodesaude.sistema.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.planodesaude.sistema.model.Clinica;
import com.planodesaude.sistema.service.ClinicaService;
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Clinica> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public Clinica salvar(@RequestBody Clinica clinica) {
        return service.salvar(clinica);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

}
