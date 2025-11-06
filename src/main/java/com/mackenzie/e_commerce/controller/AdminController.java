package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.AdminPedidoResumoDTO;
import com.mackenzie.e_commerce.dto.DashboardDTO;
import com.mackenzie.e_commerce.dto.PedidoDetalhadoDTO;
import com.mackenzie.e_commerce.dto.UpdateStatusRequestDTO;
import com.mackenzie.e_commerce.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO dashboardData = adminService.getDashboardData();
        return ResponseEntity.ok(dashboardData);
    }

    @GetMapping("/pedidos-em-andamento")
    public ResponseEntity<List<AdminPedidoResumoDTO>> getPedidosEmAndamento() {
        List<AdminPedidoResumoDTO> pedidos = adminService.listarPedidosEmAndamento();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDetalhadoDTO> getDetalhesPedido(@PathVariable Long id) {
        try {
            PedidoDetalhadoDTO pedidoDetalhado = adminService.getDetalhesPedido(id);
            return ResponseEntity.ok(pedidoDetalhado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pedidos/{id}/status")
    public ResponseEntity<PedidoDetalhadoDTO> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequestDTO dto) {
        try {
            PedidoDetalhadoDTO pedidoAtualizado = adminService.atualizarStatusPedido(id, dto);
            return ResponseEntity.ok(pedidoAtualizado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
