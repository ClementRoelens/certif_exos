package org.example.billingservice.service;

import org.example.billingservice.dto.payment.PaymentRequestDTO;
import org.example.billingservice.dto.payment.PaymentResponseDTO;
import org.example.billingservice.entity.Invoice;
import org.example.billingservice.entity.Payment;
import org.example.billingservice.repository.InvoiceRepository;
import org.example.billingservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public PaymentResponseDTO createPayment(PaymentRequestDTO DTO) {
        Invoice invoice = invoiceRepository.findById(DTO.getInvoiceId()).orElse(null);
        Payment payment = new Payment(DTO);
        payment.setInvoice(invoice);
        payment.setPaymentDate(LocalDate.now());

        Payment createdPayment = paymentRepository.save(payment);

        return new PaymentResponseDTO(payment);
    }
}
