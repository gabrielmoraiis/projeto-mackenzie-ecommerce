package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.AdminPedidoResumoDTO;
import com.mackenzie.e_commerce.dto.DashboardDTO;
import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.model.StatusPedido;
import com.mackenzie.e_commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private PedidoRepository pedidoRepository;


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
}
