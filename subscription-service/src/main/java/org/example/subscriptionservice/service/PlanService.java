package org.example.subscriptionservice.service;

import org.example.subscriptionservice.entity.Plan;
import org.example.subscriptionservice.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;

    public Plan getPlan(int id) {
        return planRepository.findById(id).orElse(null);
    }
}
