package com.mackenzie.e_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        String urlImagem,
        Long categoriaId,
        List<Long> essenciaIds,
        List<Long> opcaoAdicionalIds
) {
}