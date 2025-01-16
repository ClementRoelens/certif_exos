package org.example.billingservice.dto.payment;

import org.example.billingservice.dto.invoice.InvoiceRequestDTO;
import org.example.billingservice.entity.Payment;

import java.time.LocalDate;

public class PaymentResponseDTO {
    private LocalDate paymentDate;
    private double amount;


    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(Payment payment) {
        this.paymentDate = payment.getPaymentDate();
        this.amount = payment.getAmount();
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
