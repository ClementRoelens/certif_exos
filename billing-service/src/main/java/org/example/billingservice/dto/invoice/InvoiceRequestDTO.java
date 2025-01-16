package org.example.billingservice.dto.invoice;

import org.example.billingservice.enums.Status;

import java.time.LocalDate;

public class InvoiceRequestDTO {
    private LocalDate issueDate;
    private double totalAmount;
    private Status status;
    private String subscriptionId;


    public InvoiceRequestDTO() {
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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
