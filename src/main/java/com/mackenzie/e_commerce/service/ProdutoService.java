package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.model.Categoria;
import com.mackenzie.e_commerce.model.Produto;
import com.mackenzie.e_commerce.repository.CategoriaRepository;
import com.mackenzie.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;


    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> listarProdutos(String nome, Long categoriaId) {
        return produtoRepository.filtrarPorNomeECategoria(nome, categoriaId);
    }

    public Produto buscarProdutoPorId(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: "+ id));
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}

