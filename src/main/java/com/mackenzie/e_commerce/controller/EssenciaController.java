package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.dto.EssenciaDTO;
import com.mackenzie.e_commerce.model.Essencia;
import com.mackenzie.e_commerce.service.EssenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/essencias")
public class EssenciaController {

    @Autowired
    private EssenciaService service;

    @PostMapping
    public ResponseEntity<Essencia> criar(@RequestBody EssenciaDTO dto) {
        Essencia novaEssencia = service.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaEssencia.getId())
                .toUri();

        return ResponseEntity.created(uri).body(novaEssencia);
    }

    @GetMapping
    public ResponseEntity<List<Essencia>> listarTodas() {
        List<Essencia> essencias = service.listarTodas();
        return ResponseEntity.ok(essencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}