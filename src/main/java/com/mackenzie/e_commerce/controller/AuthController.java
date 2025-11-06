package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.ClienteDTO;
import com.mackenzie.e_commerce.dto.LoginRequestDTO;
import com.mackenzie.e_commerce.dto.LoginResponseDTO;
import com.mackenzie.e_commerce.dto.RegistroRequestDTO;
import com.mackenzie.e_commerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<ClienteDTO> registrar(
            @Valid @RequestBody RegistroRequestDTO dto) {
        try {
            ClienteDTO clienteCriado = authService.registrar(dto);
            return ResponseEntity.ok(clienteCriado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {
        try {
            LoginResponseDTO response = authService.login(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}