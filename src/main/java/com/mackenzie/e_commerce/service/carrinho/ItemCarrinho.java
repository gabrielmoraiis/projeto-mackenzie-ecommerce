package com.mackenzie.e_commerce.service.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ItemCarrinho {

    private final UUID id = UUID.randomUUID();

    private final Long produtoId;
    private int quantidade;
    private final Long essenciaId;
    private final List<Long> opcoesAdicionaisIds;
}
