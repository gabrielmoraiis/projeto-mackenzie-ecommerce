package com.mackenzie.e_commerce.dto;

import com.mackenzie.e_commerce.model.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoConsultaDTO {

    private Long id;

    private LocalDateTime dataCriacao;

    private StatusPedido status;

    private BigDecimal totalPedido;

    private List<ItemPedidoConsultaDTO> itens;
}
