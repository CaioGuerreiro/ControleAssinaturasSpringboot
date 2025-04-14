package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PaymentWebHook;
import com.example.demo.messaging.SubscriptionEventPublisher;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("/webhook/payments")
public class PaymentWebhookController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired 
    private SubscriptionEventPublisher publisher;

    @Autowired
    private PaymentRepository paymentsRepository;

    


    @PostMapping
    public ResponseEntity<PaymentWebHook> handlePaymentWebhook(@RequestBody PaymentWebHook payment) {
        System.out.println("Received payment webhook: " + payment.getSubscriptionId() + " - " + payment.getStatus());


        //update subscription status based on payment status
        Optional<Subscription> optionalSub = subscriptionService.findById(Long.valueOf(payment.getSubscriptionId()));


        if(optionalSub.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payment);
        }

        Subscription sub = optionalSub.get();

        if(payment.getStatus().equals("APPROVED")){
            sub.setStatus("ACTIVE");
            sub.setEndDate(sub.getEndDate().plusMonths(1));

        }
        else if (payment.getStatus().equals("DECLINED")){
            sub.setStatus("INACTIVE");
        }
        PaymentWebHook savedPayment = paymentsRepository.save(payment); // Esta linha é crucial

        subscriptionService.save(sub);

        // Publicar evento de pagamento

        PaymentWebHook event = new PaymentWebHook();
        event.setSubscriptionId(payment.getSubscriptionId());
        event.setStatus("PAGAMENTO_" + payment.getStatus());
        event.setAmount(payment.getAmount());
        event.setTransactionId(payment.getTransactionId());

        publisher.publishSubscriptionEvent(event.getStatus() + " id:" + event.getSubscriptionId());

        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentWebHook>> getAllPayments(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentsRepository.findAll());
    }

}
