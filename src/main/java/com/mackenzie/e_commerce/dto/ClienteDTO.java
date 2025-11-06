package com.mackenzie.e_commerce.dto;

import com.mackenzie.e_commerce.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String whatsapp;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.email = cliente.getEmail();
        this.whatsapp = cliente.getWhatsapp();
    }
}