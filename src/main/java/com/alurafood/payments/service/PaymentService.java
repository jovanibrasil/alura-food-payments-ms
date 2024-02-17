package com.alurafood.payments.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alurafood.payments.dto.PaymentDto;
import com.alurafood.payments.model.Payment;
import com.alurafood.payments.model.Status;
import com.alurafood.payments.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Page<PaymentDto> getAll(Pageable page) {
    return paymentRepository
        .findAll(page)
        .map(p -> modelMapper.map(p, PaymentDto.class));
  }

  public PaymentDto getById(Long id) {
    Payment payment = paymentRepository
        .findById(id)
        .orElseThrow(EntityNotFoundException::new);

    return modelMapper.map(payment, PaymentDto.class);
  }

  public PaymentDto create(PaymentDto paymentDto) {
    Payment payment = modelMapper.map(paymentDto, Payment.class);
    payment.setStatus(Status.CREATED);
    payment = paymentRepository.save(payment);
    return modelMapper.map(payment, PaymentDto.class);
  }

  public PaymentDto replace(Long id, PaymentDto paymentDto) {
    Payment payment = modelMapper.map(paymentDto, Payment.class);
    payment.setId(id);
    payment = paymentRepository.save(payment);
    return modelMapper.map(payment, PaymentDto.class);
  }

  public void delete(Long id) {
    paymentRepository.deleteById(id);
  }

}
