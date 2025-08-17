package com.planodesaude.sistema.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "beneficiario")
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoSaude produtoSaude;

    private LocalDate dataInclusao = LocalDate.now();
    private String cidade;
    private String bairro;
    private String estado;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String estadoCivil;

    @Column(length = 1)
    private String sexo; // M ou F

    @Column(nullable = false)
    private String status = "ativo";
}