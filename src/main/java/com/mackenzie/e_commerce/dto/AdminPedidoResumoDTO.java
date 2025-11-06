package com.mackenzie.e_commerce.dto;

import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.model.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AdminPedidoResumoDTO {

    private Long id;
    private LocalDateTime dataCriacao;
    private StatusPedido status;
    private BigDecimal totalPedido;

    private String nomeCliente;
    private String emailCliente;

    public static AdminPedidoResumoDTO fromEntity(Pedido pedido) {
        AdminPedidoResumoDTO dto = new AdminPedidoResumoDTO();
        dto.setId(pedido.getId());
        dto.setDataCriacao(pedido.getDataCriacao());
        dto.setStatus(pedido.getStatus());
        dto.setTotalPedido(pedido.getTotalPedido());

        if (pedido.getCliente() != null) {
            dto.setNomeCliente(pedido.getCliente().getNomeCompleto());
            dto.setEmailCliente(pedido.getCliente().getEmail());
        }

        return dto;
    }
}
