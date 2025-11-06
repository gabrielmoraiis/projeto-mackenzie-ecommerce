package com.mackenzie.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotBlank(message = "O campo 'rua' é obrigatório")
    private String rua;

    @NotBlank(message = "O campo 'numero' é obrigatório")
    private String numero;

    private String complemento;

    @NotBlank(message = "O campo 'bairro' é obrigatório")
    private String bairro;

    @NotBlank(message = "O campo 'cidade' é obrigatória")
    private String cidade;

    @NotBlank(message = "O campo 'estado' é obrigatório")
    private String estado;

    @NotBlank(message = "O campo 'cep' é obrigatório")
    private String cep;
}
