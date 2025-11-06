package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.CheckoutRequestDTO;
import com.mackenzie.e_commerce.dto.PedidoConsultaDTO;
import com.mackenzie.e_commerce.dto.PedidoDetalhadoDTO;
import com.mackenzie.e_commerce.model.Pedido;
import com.mackenzie.e_commerce.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
//    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PedidoDetalhadoDTO> finalizarPedido(
            @Valid @RequestBody CheckoutRequestDTO request) {
        try {
            PedidoDetalhadoDTO novoPedido = pedidoService.criarPedido(request);
            return ResponseEntity.ok(novoPedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<PedidoConsultaDTO>> consultaPedido(@RequestParam @NotBlank @Email String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<PedidoConsultaDTO> pedidos = pedidoService.consultarPedidosPorEmail(email);

        return ResponseEntity.ok(pedidos);
    }


//    @GetMapping("/{id}/pix")
//    public ResponseEntity<PixResponseDTO> gerarPix(@PathVariable Long id) {
//        try {
//            PixResponseDTO pixResponse = pagamentoService.gerarPixParaPedido(id);
//            return ResponseEntity.ok(pixResponse);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
}
