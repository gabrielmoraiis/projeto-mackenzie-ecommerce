package com.mackenzie.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private BigDecimal receitaTotal;
    private Long totalPedidosPagos;

}
