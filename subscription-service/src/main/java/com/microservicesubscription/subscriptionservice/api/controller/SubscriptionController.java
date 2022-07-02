package com.microservicesubscription.subscriptionservice.api.controller;

import com.microservicesubscription.subscriptionservice.domain.repository.SubscriptionRepository;
import com.microservicesubscription.subscriptionservice.domain.service.SubscriptionService;
import com.microservicesubscription.subscriptionservice.model.Newsletter;
import com.microservicesubscription.subscriptionservice.model.Subscription;
import com.microservicesubscription.subscriptionservice.model.dto.SubscriptionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 19:52
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/subscribers")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        log.info("Get all subscribers.");
        return subscriptionRepository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable String email) {
        log.info("get subscriber detail");
        var subscription = subscriptionRepository.findSubscriptionByEmail(email);
        return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createNewSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        var subscription = new Subscription();

        BeanUtils.copyProperties(subscriptionDTO, subscription);
        subscription.setConsent(subscriptionDTO.isConsent());

        subscription.setNewsletter(new Newsletter());
        subscription.getNewsletter().setId(subscriptionDTO.getNewsletter_id());

        return subscriptionService.subscribe(subscription);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable String email) {
        if (subscriptionRepository.findSubscriptionByEmail(email).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        subscriptionService.unSubscribe(email);
        return ResponseEntity.noContent().build();
    }

}
