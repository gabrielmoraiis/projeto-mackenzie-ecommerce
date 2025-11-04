package com.mackenzie.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    private Long produtoId;
    private String nomeProduto;
    private int quantidade;
    private String essenciaEscolhida;

    private String opcoesAdicionais;

    private BigDecimal precoUnitarioSnapshot;
    private BigDecimal subtotalSnapshot;
}
