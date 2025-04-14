package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PaymentWebHook {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;
    private String subscriptionId;
    private String status; // APPROVED, DECLINED
    private Double amount;
    private String transactionId;
    private LocalDateTime processedAt = LocalDateTime.now();

    // Construtor padrão obrigatório
    public PaymentWebHook() {
    }

    // Construtor com campos
    public PaymentWebHook(String subscriptionId, String status, Double amount, String transactionId) {
        this.subscriptionId = subscriptionId;
        this.status = status;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    // Getters e Setters para todos os campos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
}