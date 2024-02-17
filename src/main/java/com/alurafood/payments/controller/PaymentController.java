package com.alurafood.payments.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alurafood.payments.dto.PaymentDto;
import com.alurafood.payments.service.PaymentService;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping
  public Page<PaymentDto> getPayments(@PageableDefault(size = 10) Pageable pageable) {
    return paymentService.getAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentDto> getPaymentById(@PathVariable @NotNull Long id) {
    return ResponseEntity.ok(paymentService.getById(id));
  }

  @PostMapping
  public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto paymentDto, UriComponentsBuilder uriBuilder) {
    PaymentDto payment = paymentService.create(paymentDto);

    URI localtionAddress = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

    return ResponseEntity.created(localtionAddress).body(payment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaymentDto> update(@PathVariable Long id, @RequestBody PaymentDto paymentDto) {
    return ResponseEntity.ok(paymentService.replace(id, paymentDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PaymentDto> delete(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
