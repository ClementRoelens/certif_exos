package org.example.subscriptionservice.dto;

import org.example.subscriptionservice.entity.Plan;

import java.time.LocalDate;

public class SubscriptionRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private int planId;
    private int clientId;


    public SubscriptionRequestDTO() {}


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

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
