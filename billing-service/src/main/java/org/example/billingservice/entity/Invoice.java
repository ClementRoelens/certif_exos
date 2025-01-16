package org.example.billingservice.entity;

import jakarta.persistence.*;
import org.example.billingservice.dto.invoice.InvoiceRequestDTO;
import org.example.billingservice.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate issueDate;
    private double totalAmount;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @OneToMany(mappedBy = "invoice")
    private List<Payment> payments;


    public Invoice() {
    }

    public Invoice(InvoiceRequestDTO DTO) {
        this.issueDate = DTO.getIssueDate();
        this.totalAmount = DTO.getTotalAmount();
        this.status = DTO.getStatus();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
