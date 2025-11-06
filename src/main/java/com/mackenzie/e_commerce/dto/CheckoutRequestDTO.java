package com.mackenzie.e_commerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequestDTO {
    private String nomeCompleto;
    private String email;
    private String whatsapp;
    @Valid
    @NotNull(message = "O endereço é obrigatório")
    private EnderecoDTO endereco;
    private UUID directBuyItemId;
}
