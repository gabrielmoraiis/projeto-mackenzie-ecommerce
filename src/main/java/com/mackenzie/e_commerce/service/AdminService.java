package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.*;
import com.mackenzie.e_commerce.model.ItemPedido;
import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.model.StatusPedido;
import com.mackenzie.e_commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private static final EnumSet<StatusPedido> STATUS_MANUAIS_PERMITIDOS = EnumSet.of(
            StatusPedido.PEDIDO_RECEBIDO,
            StatusPedido.EM_PRODUCAO,
            StatusPedido.PRONTO
    );

    public DashboardDTO getDashboardData() {
        BigDecimal receitaTotal = pedidoRepository.getReceitaTotal();
        Long totalPedidos = pedidoRepository.countPedidosPagos();

        if (receitaTotal == null) {
            receitaTotal = BigDecimal.ZERO;
        }

        return new DashboardDTO(receitaTotal, totalPedidos);
    }

    public List<AdminPedidoResumoDTO> listarPedidosEmAndamento() {

        List<StatusPedido> statusesEmAndamento = List.of(
                StatusPedido.PAGAMENTO_CONFIRMADO,
                StatusPedido.PEDIDO_RECEBIDO,
                StatusPedido.EM_PRODUCAO,
                StatusPedido.PRONTO
        );

        List<Pedido> pedidos = pedidoRepository.findByStatusInOrderByDataCriacaoAsc(
                statusesEmAndamento
        );

        return pedidos.stream()
                .map(AdminPedidoResumoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PedidoDetalhadoDTO getDetalhesPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        List<ItemPedidoConsultaDTO> itensDTO = pedido.getItens().stream()
                .map(this::mapItemPedidoToConsultaDTO)
                .collect(Collectors.toList());


        return new PedidoDetalhadoDTO(pedido, itensDTO);
    }

    public PedidoDetalhadoDTO atualizarStatusPedido(Long id, UpdateStatusRequestDTO dto) {

        StatusPedido newStatus = dto.getNewStatus();

        if (newStatus == null || !STATUS_MANUAIS_PERMITIDOS.contains(newStatus)) {
            throw new IllegalArgumentException(
                    "Atualização de status inválida. Status permitidos: " +
                            STATUS_MANUAIS_PERMITIDOS
            );
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        pedido.setStatus(newStatus);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        List<ItemPedidoConsultaDTO> itensDTO = pedidoSalvo.getItens().stream()
                .map(this::mapItemPedidoToConsultaDTO)
                .collect(Collectors.toList());

        return new PedidoDetalhadoDTO(pedidoSalvo, itensDTO);
    }

    private ItemPedidoConsultaDTO mapItemPedidoToConsultaDTO(ItemPedido item) {
        ItemPedidoConsultaDTO itemDTO = new ItemPedidoConsultaDTO();
        itemDTO.setNomeProduto(item.getNomeProduto());
        itemDTO.setQuantidade(item.getQuantidade());
        itemDTO.setEssenciaEscolhida(item.getEssenciaEscolhida());
        itemDTO.setOpcoesAdicionais(item.getOpcoesAdicionais());
        itemDTO.setSubTotalSnapshot(item.getSubtotalSnapshot());
        return itemDTO;
    }
}
