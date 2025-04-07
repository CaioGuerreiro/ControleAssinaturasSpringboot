package com.example.demo.config;

import java.util.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "subscriptionQueue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    // @Bean
    // public RabbitTemplate rabbitTemplate(@Autowired ConnectionFactory connectionFactory){
    //     RabbitTemplate template = new RabbitTemplate();
    //     template.setConnectionFactory(connectionFactory);
    //     template.setMessageConverter(jsonMessageConverter());

    //     template.setMandatory(true);
    //     template.setChannelTransacted(true);

    //     return template;
    // }

    // @Bean
    // public Jackson2JsonMessageConverter jsonMessageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }
}
