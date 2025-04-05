package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
public record SubscriptionRecordDto(@NotBlank(message = "user Id é obrigatorio") String userId,
                                    @NotBlank(message = "Plano do usuário é obrigatorio") String plan) {
    // Campos obrigatórios para criar uma assinatura
    // Adicione outros campos conforme necessário

}
