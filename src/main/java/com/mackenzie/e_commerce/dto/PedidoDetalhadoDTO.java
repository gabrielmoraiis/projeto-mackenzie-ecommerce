package com.mackenzie.e_commerce.dto;

import com.mackenzie.e_commerce.model.Endereco;
import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.model.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDetalhadoDTO {

    private Long id;
    private StatusPedido status;
    private BigDecimal totalPedido;
    private LocalDateTime dataCriacao;

    private ClienteDTO cliente;
    private EnderecoDTO endereco;
    private List<ItemPedidoConsultaDTO> itens;


    public PedidoDetalhadoDTO(Pedido pedido, List<ItemPedidoConsultaDTO> itensMapeados) {
        this.id = pedido.getId();
        this.status = pedido.getStatus();
        this.totalPedido = pedido.getTotalPedido();
        this.dataCriacao = pedido.getDataCriacao();

        this.cliente = new ClienteDTO(pedido.getCliente());

        this.endereco = mapEnderecoToEnderecoDTO(pedido.getEndereco());

        this.itens = itensMapeados;
    }

    private EnderecoDTO mapEnderecoToEnderecoDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        return dto;
    }
}
