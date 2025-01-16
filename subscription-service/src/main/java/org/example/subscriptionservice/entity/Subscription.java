package org.example.subscriptionservice.entity;

import jakarta.persistence.*;
import org.example.subscriptionservice.dto.SubscriptionRequestDTO;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Client client;
    @OneToOne
    private Plan plan;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;



    public Subscription() {
    }

    public Subscription(SubscriptionRequestDTO DTO){
        this.startDate = DTO.getStartDate();
        this.endDate = DTO.getEndDate();
        status = true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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
}
