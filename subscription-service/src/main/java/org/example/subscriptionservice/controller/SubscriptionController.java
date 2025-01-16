package org.example.subscriptionservice.controller;

import org.example.subscriptionservice.dto.SubscriptionRequestDTO;
import org.example.subscriptionservice.dto.SubscriptionResponseDTO;
import org.example.subscriptionservice.entity.Client;
import org.example.subscriptionservice.entity.Subscription;
import org.example.subscriptionservice.service.PlanService;
import org.example.subscriptionservice.service.SubscriptionService;
import org.example.subscriptionservice.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.subscriptionservice.util.ApiInformations.*;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private PlanService planService;
    private final String API_ROOT = "http://localhost:";

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> subscribe(@RequestBody SubscriptionRequestDTO subscriptionDto) {
        Subscription subscription = new Subscription(subscriptionDto);

        RestClient<Client> restClient = new RestClient<>(API_ROOT + CLIENT_PORT + CLIENT_SUFFIX + "/" + subscriptionDto.getClientId());
        Client client = restClient.getRequest(Client.class);
        subscription.setClient(client);
        subscription.setPlan(planService.getPlan(subscriptionDto.getPlanId()));

        Subscription createdSubscription = subscriptionService.createSubscription(subscription);

        if (createdSubscription != null) {
//            RestClient<BillingRequest>
            SubscriptionResponseDTO response = new SubscriptionResponseDTO(subscription);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable int id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);

        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable int id, @RequestBody Subscription subscription) {
        Subscription toUpdateSubscription = subscriptionService.getSubscriptionById(id);

        if (toUpdateSubscription != null) {
            Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
            if (updatedSubscription != null) {
                return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable int id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);

        if (subscription != null) {
            subscription.setStatus(false);
            Subscription updatedSubcription = subscriptionService.updateSubscription(subscription);

            if (updatedSubcription != null) {
                return new ResponseEntity<>(updatedSubcription, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
