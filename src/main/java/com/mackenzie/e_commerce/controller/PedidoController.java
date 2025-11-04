package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.CheckoutRequestDTO;
import com.mackenzie.e_commerce.dto.PixResponseDTO;
import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.service.PagamentoService;
import com.mackenzie.e_commerce.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pedido> finalizarPedido(@RequestBody CheckoutRequestDTO request) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(request);
            return ResponseEntity.ok(novoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}/pix")
    public ResponseEntity<PixResponseDTO> gerarPix(@PathVariable Long id) {
        try {
            PixResponseDTO pixResponse = pagamentoService.gerarPixParaPedido(id);
            return ResponseEntity.ok(pixResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
