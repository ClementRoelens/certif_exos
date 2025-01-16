package org.example.billingservice.dto.payment;

import java.time.LocalDate;

public class PaymentRequestDTO {
    private double amount;
    private int invoiceId;


    public PaymentRequestDTO() {
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
