package org.example.billingservice.entity;

import jakarta.persistence.*;
import org.example.billingservice.dto.payment.PaymentRequestDTO;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate paymentDate;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


    public Payment() {
    }

    public Payment(PaymentRequestDTO paymentRequestDTO) {
        this.amount = paymentRequestDTO.getAmount();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
