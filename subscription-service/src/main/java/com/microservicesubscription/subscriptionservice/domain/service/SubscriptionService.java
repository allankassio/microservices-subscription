package com.microservicesubscription.subscriptionservice.domain.service;

import com.microservicesubscription.subscriptionservice.model.Subscription;

import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 21:40
 */
public interface SubscriptionService {

    UUID subscribe(Subscription subscription);

    void unSubscribe(String email);

}
