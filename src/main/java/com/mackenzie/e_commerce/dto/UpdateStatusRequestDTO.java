package com.mackenzie.e_commerce.dto;

import com.mackenzie.e_commerce.model.StatusPedido;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequestDTO {

    @NotNull(message = "O novo status não pode ser nulo")
    private StatusPedido newStatus;

}