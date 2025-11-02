package com.mackenzie.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AdicionarItemRequestDTO {

    private Long produtoId;

    private int quantidade;

    private Long essenciaId;

    private List<Long> opcoesAdicionaisIds;
}
