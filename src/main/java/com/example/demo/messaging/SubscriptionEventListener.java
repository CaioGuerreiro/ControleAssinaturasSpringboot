package com.example.demo.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.service.SubscriptionService;

@Component
public class SubscriptionEventListener {

    @Autowired
    private SubscriptionService subscriptionService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleSubscriptionEvent(String message){
        System.out.println("Received event: " + message);

         // Aqui você pode processar upgrades, renovações ou cancelamentos de assinaturas
    }
}
