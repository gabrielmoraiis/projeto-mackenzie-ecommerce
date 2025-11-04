package com.mackenzie.e_commerce.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
