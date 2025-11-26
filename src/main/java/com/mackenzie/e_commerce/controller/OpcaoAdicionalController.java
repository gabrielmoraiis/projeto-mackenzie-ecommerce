package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.OpcaoAdicionalDTO;
import com.mackenzie.e_commerce.model.OpcaoAdicional;
import com.mackenzie.e_commerce.service.OpcaoAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/opcoes-adicionais")
public class OpcaoAdicionalController {

    @Autowired
    private OpcaoAdicionalService service;

    @PostMapping
    public ResponseEntity<OpcaoAdicional> criar(@RequestBody OpcaoAdicionalDTO dto) {
        OpcaoAdicional novaOpcao = service.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaOpcao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(novaOpcao);
    }

    @GetMapping
    public ResponseEntity<List<OpcaoAdicional>> listarTodas() {
        List<OpcaoAdicional> opcoes = service.listarTodas();
        return ResponseEntity.ok(opcoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}