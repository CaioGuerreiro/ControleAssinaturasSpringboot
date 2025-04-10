package com.example.demo.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitMQConfig;


@Component
public class SubscriptionEventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishSubscriptionEvent( String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
    }
}
