package org.example.subscriptionservice.service;

import org.example.subscriptionservice.entity.Subscription;
import org.example.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(int id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription updateSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

//    public void deleteSubscriptionById(int id) {
//        subscriptionRepository.deleteById(id);
//    }
}
