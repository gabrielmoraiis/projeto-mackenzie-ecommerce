package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.AdicionarItemRequestDTO;
import com.mackenzie.e_commerce.dto.CarrinhoViewDTO;
import com.mackenzie.e_commerce.dto.ItemCarrinhoDTO;
import com.mackenzie.e_commerce.model.Essencia;
import com.mackenzie.e_commerce.model.OpcaoAdicional;
import com.mackenzie.e_commerce.model.Produto;
import com.mackenzie.e_commerce.repository.EssenciaRepository;
import com.mackenzie.e_commerce.repository.OpcaoAdicionalRepository;
import com.mackenzie.e_commerce.repository.ProdutoRepository;
import com.mackenzie.e_commerce.service.carrinho.ItemCarrinho;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@AllArgsConstructor
public class CarrinhoDeComprasService {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    private final ProdutoRepository produtoRepository;
    private final EssenciaRepository essenciaRepository;

    private final OpcaoAdicionalRepository opcaoAdicionalRepository;

    public void adicionarItem(AdicionarItemRequestDTO request) {
        if (request.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        ItemCarrinho novoItem = new ItemCarrinho(
                request.getProdutoId(),
                request.getQuantidade(),
                request.getEssenciaId(),
                request.getOpcoesAdicionaisIds() != null ? request.getOpcoesAdicionaisIds() : Collections.emptyList()
        );
        itens.add(novoItem);
    }

    public void removerItem(UUID itemId) {
        itens.removeIf(item -> item.getId().equals(itemId));
    }

    public void limparCarrinho() {
        itens.clear();
    }

    public CarrinhoViewDTO getVisaoCarrinho() {
        List<ItemCarrinhoDTO> itensDTO = new ArrayList<>();
        BigDecimal totalPedido = BigDecimal.ZERO;

        for (ItemCarrinho item : itens) {
            Produto p = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal precoUnitario = p.getPreco();

            String nomeEssencia = null;
            if (item.getEssenciaId() != null) {
                Essencia e = essenciaRepository.findById(item.getEssenciaId())
                        .orElseThrow(() -> new RuntimeException("Essência não encontrada"));
                nomeEssencia = e.getNome();
            }

            List<String> nomesOpcoes = new ArrayList<>();
            if (item.getOpcoesAdicionaisIds() != null && !item.getOpcoesAdicionaisIds().isEmpty()) {
                List<OpcaoAdicional> ops = opcaoAdicionalRepository.findAllById(item.getOpcoesAdicionaisIds());
                for (OpcaoAdicional op : ops) {
                    precoUnitario = precoUnitario.add(op.getPrecoAdicional());
                    nomesOpcoes.add(op.getNome());
                }
            }

            BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(item.getQuantidade()));

            ItemCarrinhoDTO dto = new ItemCarrinhoDTO();
            dto.setItemId(item.getId());
            dto.setProdutoId(p.getId());
            dto.setNomeProduto(p.getNome());
            dto.setUrlImagem(p.getUrlImagem());
            dto.setQuantidade(item.getQuantidade());
            dto.setEssenciaEscolhida(nomeEssencia);
            dto.setOpcaoAdicionais(nomesOpcoes);
            dto.setPrecoUnitario(precoUnitario);
            dto.setSubTotal(subtotal);

            itensDTO.add(dto);
            totalPedido = totalPedido.add(subtotal);
        }

        return new CarrinhoViewDTO(itensDTO, totalPedido);
    }

    public CarrinhoViewDTO getVisaoItemUnico(UUID itemId) {
        ItemCarrinho item = itens.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado para compra direta"));

        Produto p = produtoRepository.findById(item.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        BigDecimal precoUnitario = p.getPreco();
        String nomeEssencia = null;
        List<String> nomesOpcoes = new ArrayList<>();

        if (item.getEssenciaId() != null) {
            Essencia e = essenciaRepository.findById(item.getEssenciaId())
                    .orElseThrow(() -> new RuntimeException("Essência não encontrada"));
            nomeEssencia = e.getNome();
        }

        if (item.getOpcoesAdicionaisIds() != null && !item.getOpcoesAdicionaisIds().isEmpty()) {
            List<OpcaoAdicional> ops = opcaoAdicionalRepository.findAllById(item.getOpcoesAdicionaisIds());
            for (OpcaoAdicional op : ops) {
                precoUnitario = precoUnitario.add(op.getPrecoAdicional());
                nomesOpcoes.add(op.getNome());
            }
        }

        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(item.getQuantidade()));

        ItemCarrinhoDTO dto = new ItemCarrinhoDTO();
        dto.setItemId(item.getId());
        dto.setProdutoId(p.getId());
        dto.setNomeProduto(p.getNome());
        dto.setUrlImagem(p.getUrlImagem());
        dto.setQuantidade(item.getQuantidade());
        dto.setEssenciaEscolhida(nomeEssencia);
        dto.setOpcaoAdicionais(nomesOpcoes);
        dto.setPrecoUnitario(precoUnitario);
        dto.setSubTotal(subtotal);

        List<ItemCarrinhoDTO> listaItemUnico = Collections.singletonList(dto);

        return new CarrinhoViewDTO(listaItemUnico, subtotal);
    }
}
