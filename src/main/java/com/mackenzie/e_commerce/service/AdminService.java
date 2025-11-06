package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.DashboardDTO;
import com.mackenzie.e_commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
