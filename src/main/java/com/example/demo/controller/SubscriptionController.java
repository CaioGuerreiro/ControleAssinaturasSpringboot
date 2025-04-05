package com.example.demo.controller;

import com.example.demo.dto.SubscriptionRecordDto;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.SubscriptionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SubscriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    //@PostMapping("/subscription")
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody @Valid SubscriptionRecordDto subscriptionDto) {
        Subscription created = subscriptionService.createSubscription(subscriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }



    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getOneSubscription(@PathVariable Long id){
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(subscription.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long id){
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }

        subscriptionRepository.delete(subscription.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Subscription deleted successfully");
    }

}
