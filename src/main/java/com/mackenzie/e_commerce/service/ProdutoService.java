package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.ProdutoDTO;
import com.mackenzie.e_commerce.model.Categoria;
import com.mackenzie.e_commerce.model.Essencia;
import com.mackenzie.e_commerce.model.OpcaoAdicional;
import com.mackenzie.e_commerce.model.Produto;
import com.mackenzie.e_commerce.repository.CategoriaRepository;
import com.mackenzie.e_commerce.repository.EssenciaRepository;
import com.mackenzie.e_commerce.repository.OpcaoAdicionalRepository;
import com.mackenzie.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EssenciaRepository essenciaRepository;
    private final OpcaoAdicionalRepository opcaoAdicionalRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, EssenciaRepository essenciaRepository, OpcaoAdicionalRepository opcaoAdicionalRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.essenciaRepository = essenciaRepository;
        this.opcaoAdicionalRepository = opcaoAdicionalRepository;
    }

    public List<Produto> listarProdutos(String nome, Long categoriaId) {
        return produtoRepository.filtrarPorNomeECategoria(nome, categoriaId);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: "+ id));
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Produto criarProduto(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.categoriaId()));

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setUrlImagem(dto.urlImagem());
        produto.setCategoria(categoria);

        if (dto.essenciaIds() != null && !dto.essenciaIds().isEmpty()) {
            List<Essencia> essencias = essenciaRepository.findAllById(dto.essenciaIds());
            produto.setEssenciasDisponiveis(essencias);
        }

        if (dto.opcaoAdicionalIds() != null && !dto.opcaoAdicionalIds().isEmpty()) {
            List<OpcaoAdicional> opcoes = opcaoAdicionalRepository.findAllById(dto.opcaoAdicionalIds());
            produto.setOpcoesAdicionais(opcoes);
        }

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado para exclusão");
        }
        produtoRepository.deleteById(id);
    }

}

