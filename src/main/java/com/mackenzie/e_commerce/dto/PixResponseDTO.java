package com.mackenzie.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PixResponseDTO {
    private Long pedidoId;
    private String qrCodeBase64;
    private String qrCodeCopiaECola;
}
