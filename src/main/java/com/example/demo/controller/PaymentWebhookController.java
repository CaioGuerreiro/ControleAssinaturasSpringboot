package com.example.demo.controller;

import java.util.Optional;
import java.util.concurrent.Flow.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PaymentWebHook;
import com.example.demo.messaging.SubscriptionEventPublisher;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("/webhook/payments")
public class PaymentWebhookController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired 
    private SubscriptionEventPublisher publisher;


    @PostMapping
    public ResponseEntity<PaymentWebHook> handlePaymentWebhook(@RequestBody PaymentWebHook payment) {
        System.out.println("Received payment webhook: " + payment.getSubscriptionId() + " - " + payment.getStatus());


        //update subscription status based on payment status
        Optional<Subscription> optionalSub = subscriptionService.findById(Long.valueOf(payment.getSubscriptionId()));


        if(optionalSub.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found");
        }
    }

}
