package com.mackenzie.e_commerce.controller;

import com.mackenzie.e_commerce.model.Categoria;
import com.mackenzie.e_commerce.model.Produto;
import com.mackenzie.e_commerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        try {
            Produto produto = produtoService.buscarProdutoPorId(id);
            return  ResponseEntity.ok().body(produto);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = produtoService.listarCategorias();
        return ResponseEntity.ok().body(categorias);
    }
}
