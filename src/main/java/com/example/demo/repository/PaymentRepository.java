package com.example.demo.repository;

import com.example.demo.entity.PaymentWebHook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentWebHook, Long> {
    // Métodos customizados podem ser adicionados aqui
}