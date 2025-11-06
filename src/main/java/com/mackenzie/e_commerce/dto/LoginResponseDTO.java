package com.mackenzie.e_commerce.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private String tokenJwt;

    public LoginResponseDTO(String tokenJwt) {
        this.tokenJwt = tokenJwt;
    }
}
