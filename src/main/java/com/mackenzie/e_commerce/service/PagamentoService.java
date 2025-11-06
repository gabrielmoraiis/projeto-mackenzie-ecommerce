//package com.mackenzie.e_commerce.service;
//
//import com.mackenzie.e_commerce.dto.PixResponseDTO;
//import com.mackenzie.e_commerce.model.Cliente;
//import com.mackenzie.e_commerce.model.Pedido;
//import com.mackenzie.e_commerce.repository.PedidoRepository;
//import com.mercadopago.client.common.IdentificationRequest;
//import com.mercadopago.client.payment.PaymentClient;
//import com.mercadopago.client.payment.PaymentCreateRequest;
//import com.mercadopago.client.payment.PaymentPayerRequest;
//import com.mercadopago.exceptions.MPException;
//import com.mercadopago.resources.payment.Payment;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.OffsetDateTime;
//
//@Service
//@AllArgsConstructor
//public class PagamentoService {
//
//    private final PedidoRepository pedidoRepository;
//
//    public PixResponseDTO gerarPixParaPedido(Long pedidoId) {
//        Pedido pedido = pedidoRepository.findById(pedidoId)
//                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
//
//        Cliente cliente = pedido.getCliente();
//
//        try {
//            IdentificationRequest identification = IdentificationRequest.builder()
//                    .type("CPF")
//                    .number(cliente.getC)
//                    .build();
//
//            PaymentPayerRequest payer = PaymentPayerRequest.builder()
//                    .email(cliente.getEmail())
//                    .firstName(cliente.getNomeCompleto().split(" ")[0])
//                    .lastName(cliente.getNomeCompleto().substring(cliente.getNomeCompleto().indexOf(" ") + 1))
//                    .identification(identification)
//                    .build();
//
//            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
//                    .transactionAmount(pedido.getTotalPedido())
//                    .description("Pedido #" + pedido.getId() + " - " + pedido.getItens().get(0).getNomeProduto())
//                    .paymentMethodId("pix")
//                    .payer(payer)
//                    .dateOfExpiration(OffsetDateTime.now().plusMinutes(30))
//                    .build();
//
//            PaymentClient client = new PaymentClient();
//            Payment payment = client.create(createRequest);
//
//            String qrCodeBase64 = payment.getPointOfInteraction().getTransactionData().getQrCodeBase64();
//            String qrCodeCopiaECola = payment.getPointOfInteraction().getTransactionData().getQrCode();
//            return new PixResponseDTO(pedidoId, qrCodeBase64, qrCodeCopiaECola);
//        } catch (MPException e) {
//            System.err.println("Erro API Mercado Pago: " + e.getMessage());
//            throw new RuntimeException("Erro ao gerar PIX: " + e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException("Erro inesperado ao gerar PIX", e);
//        }
//    }
//}
