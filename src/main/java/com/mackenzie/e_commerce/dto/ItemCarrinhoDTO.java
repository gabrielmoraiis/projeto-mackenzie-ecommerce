package com.mackenzie.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class ItemCarrinhoDTO {

    private UUID itemId;

    private Long produtoId;
    private String nomeProduto;
    private String urlImagem;
    private int quantidade;

    private String essenciaEscolhida;
    private List<String> opcaoAdicionais;

    private BigDecimal precoUnitario;
    private BigDecimal subTotal;
}
