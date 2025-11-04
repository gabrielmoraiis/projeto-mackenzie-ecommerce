package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.CarrinhoViewDTO;
import com.mackenzie.e_commerce.dto.CheckoutRequestDTO;
import com.mackenzie.e_commerce.dto.EnderecoDTO;
import com.mackenzie.e_commerce.dto.ItemCarrinhoDTO;
import com.mackenzie.e_commerce.model.*;
import com.mackenzie.e_commerce.repository.ClienteRepository;
import com.mackenzie.e_commerce.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final CarrinhoDeComprasService carrinhoDeComprasService;

    @Transactional
    public Pedido criarPedido(CheckoutRequestDTO request) {

        Cliente cliente = findOrCreateCliente(request);

        Endereco endereco = montarEndereco(request.getEndereco());

        CarrinhoViewDTO carrinhoView = getItensParaCheckout(request.getDirectBuyItemId());

        if (carrinhoView.getItens().isEmpty()) {
            throw new RuntimeException("Não é possível criar um pedido sem itens.");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEndereco(endereco);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setTotalPedido(carrinhoView.getTotalPedido());


        List<ItemPedido> itensDoPedido = new ArrayList<>();
        for (ItemCarrinhoDTO itemDTO : carrinhoView.getItens()) {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProdutoId(itemDTO.getProdutoId());
            item.setNomeProduto(itemDTO.getNomeProduto());
            item.setQuantidade(itemDTO.getQuantidade());
            item.setEssenciaEscolhida(itemDTO.getEssenciaEscolhida());
            item.setPrecoUnitarioSnapshot(itemDTO.getPrecoUnitario());
            item.setSubtotalSnapshot(itemDTO.getSubTotal());

            if (itemDTO.getOpcaoAdicionais() != null && !itemDTO.getOpcaoAdicionais().isEmpty()) {
                item.setOpcoesAdicionais(String.join(", ", itemDTO.getOpcaoAdicionais()));
            }
            itensDoPedido.add(item);
        }

        pedido.setItens(itensDoPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        if (request.getDirectBuyItemId() != null) {
            carrinhoDeComprasService.removerItem(request.getDirectBuyItemId());
        } else {
            carrinhoDeComprasService.limparCarrinho();
        }

        return pedidoSalvo;
    }

    private CarrinhoViewDTO getItensParaCheckout(UUID directBuyItemId) {
        if (directBuyItemId != null) {
            return carrinhoDeComprasService.getVisaoItemUnico(directBuyItemId);
        } else {
            return carrinhoDeComprasService.getVisaoCarrinho();
        }
    }

    private Cliente findOrCreateCliente(CheckoutRequestDTO request) {
        return clienteRepository.findByEmail(request.getEmail())
                .map(clienteExistente -> {
                    clienteExistente.setNomeCompleto(request.getNomeCompleto());
                    clienteExistente.setWhatsapp(request.getWhatsapp());
                    return clienteRepository.save(clienteExistente);
                })
                .orElseGet(() -> {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNomeCompleto(request.getNomeCompleto());
                    novoCliente.setEmail(request.getEmail());
                    novoCliente.setWhatsapp(request.getWhatsapp());
                    return clienteRepository.save(novoCliente);
                });
    }

    private Endereco montarEndereco(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        return endereco;
    }
}
