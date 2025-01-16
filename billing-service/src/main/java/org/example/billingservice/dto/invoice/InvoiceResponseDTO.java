package org.example.billingservice.dto.invoice;

import org.example.billingservice.entity.Invoice;
import org.example.billingservice.entity.Payment;
import org.example.billingservice.entity.Subscription;
import org.example.billingservice.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class InvoiceResponseDTO {
    private LocalDate issueDate;
    private double totalAmount;
    private Status status;
    private Subscription subscription;
    private List<Payment> payments;


    public InvoiceResponseDTO() {
    }

    public InvoiceResponseDTO(Invoice invoice) {
        this.issueDate = invoice.getIssueDate();
        this.totalAmount = invoice.getTotalAmount();
        this.status = invoice.getStatus();
        this.payments = invoice.getPayments();
        this.subscription = invoice.getSubscription();
    }


    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
