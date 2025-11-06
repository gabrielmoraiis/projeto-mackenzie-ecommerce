package com.mackenzie.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoConsultaDTO {

    private String nomeProduto;

    private int quantidade;

    private String essenciaEscolhida;

    private String opcoesAdicionais;

    private BigDecimal subTotalSnapshot;
}
