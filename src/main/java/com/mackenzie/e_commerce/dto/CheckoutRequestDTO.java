package com.mackenzie.e_commerce.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequestDTO {
    private String nomeCompleto;
    private String email;
    private String whatsapp;
    private EnderecoDTO endereco;
    private UUID directBuyItemId;
}
