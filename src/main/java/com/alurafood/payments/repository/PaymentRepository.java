package com.alurafood.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alurafood.payments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}