package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record WebhookRecordDto (@NotBlank(message = "user Id é obrigatorio") String subscriptionId,
                                @NotBlank(message = "Plano do usuário é obrigatorio") String status,
                                @NotBlank(message = "Valor do plano é obrigatorio") Double amount,
                                @NotBlank(message = "Id da transação é obrigatorio") String transactionId) {
    // Campos obrigatórios para criar uma assinatura
    // Adicione outros campos conforme necessário

}


