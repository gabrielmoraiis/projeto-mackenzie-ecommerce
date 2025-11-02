package com.mackenzie.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CarrinhoViewDTO {

    private List<ItemCarrinhoDTO> itens;
    private BigDecimal totalPedido;

}
