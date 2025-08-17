package com.planodesaude.sistema.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "procedimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigoTUSS; // Código TUSS

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer diasCarencia; // Quantos dias após inclusão pode realizar

    @Column(nullable = false)
    private Integer quantidadeMaxima; // Máximo permitido no período

    @Column(length = 1)
    private String sexoPermitido; // M, F ou null para ambos

    @Column(nullable = false)
    private Boolean autorizacaoAutomatica = true;

    @Column(nullable = false)
    private String periodoQtd = "ano"; // dia, mes, semestre, ano

   
}
