package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.AdicionarItemRequestDTO;
import com.mackenzie.e_commerce.dto.CarrinhoViewDTO;
import com.mackenzie.e_commerce.service.CarrinhoDeComprasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoDeComprasService carrinhoDeComprasService;

    @PostMapping
    public ResponseEntity<Void> adicionarAoCarrinho(@RequestBody AdicionarItemRequestDTO request) {
        try {
            carrinhoDeComprasService.adicionarItem(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<CarrinhoViewDTO> getCarrinho() {
        return ResponseEntity.ok(carrinhoDeComprasService.getVisaoCarrinho());
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removerDoCarrinho(@PathVariable UUID itemId) {
        carrinhoDeComprasService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }

}
