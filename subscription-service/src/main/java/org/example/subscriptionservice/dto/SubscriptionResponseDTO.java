package org.example.subscriptionservice.dto;

import org.example.subscriptionservice.entity.Client;
import org.example.subscriptionservice.entity.Plan;
import org.example.subscriptionservice.entity.Subscription;

import java.time.LocalDate;

public class SubscriptionResponseDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
    private Plan plan;
    private Client client;


   public SubscriptionResponseDTO(Subscription subscription) {
       this.startDate = subscription.getStartDate();
       this.endDate = subscription.getEndDate();
       this.status = subscription.isStatus();
       this.plan = subscription.getPlan();
       this.client = subscription.getClient();
   }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
