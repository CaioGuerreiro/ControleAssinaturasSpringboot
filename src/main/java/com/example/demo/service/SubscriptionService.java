package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.SubscriptionRecordDto;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.SubscriptionRepository;

import org.springframework.stereotype.Service;


@Service 
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository; // Corrigido para SubscriptionRepository

    // Método atualizado para receber DTO
    public Subscription createSubscription(SubscriptionRecordDto subscriptionDto) {
        Subscription subscription = new Subscription();
        
        // Mapeamento dos campos do DTO para a entidade
        subscription.setUserId(subscriptionDto.userId());
        subscription.setPlan(subscriptionDto.plan());
        
        // Configuração dos valores padrão
        subscription.setStatus("ACTIVE");
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        
        return subscriptionRepository.save(subscription);
    }

}