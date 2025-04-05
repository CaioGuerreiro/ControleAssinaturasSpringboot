package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.entity.Subscription;


import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find subscriptions by userId or status
    Optional<Subscription> findByUserId(String userId);

}
