package com.mackenzie.e_commerce.repository;

import com.mackenzie.e_commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:categoriaId IS NULL OR p.categoria.id = :categoriaId)")
    List<Produto> filtrarPorNomeECategoria(
            @Param("nome") String nome,
            @Param("categoriaId") Long categoriaId
    );

    @Query("SELECT p FROM Produto p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:categoriaId IS NULL OR p.categoria.id = :categoriaId)")
    List<Produto> buscarComFiltros(@Param("nome") String nome, @Param("categoriaId") Long categoriaId);


}
