package com.microservicesubscription.subscriptionservice.domain.service;

import com.microservicesubscription.subscriptionservice.domain.exception.DomainException;
import com.microservicesubscription.subscriptionservice.domain.repository.SubscriptionRepository;
import com.microservicesubscription.subscriptionservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 21:42
 */
@Slf4j
@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final String KAFKA_TOPIC = "subscription-system-subscribe-successful";

    private final SubscriptionRepository subscriptionRepository;
    private final KafkaTemplate<String, Subscription> kafkaTemplate;


    @Override
    public UUID subscribe(Subscription subscription) {
        var emailExists = subscriptionRepository.findSubscriptionByEmail(subscription.getEmail());

        if (emailExists.isPresent()) {
            throw new DomainException(String.format("email %s is already subscribed!", subscription.getEmail()));
        }

        UUID id = subscriptionRepository.save(subscription).getId();

        log.info(String.format("User with id: %s was successfully subscribed!", id));

        sendRequestToKafka(subscription);

        return id;
    }

    @Override
    public void unSubscribe(String email) {
        var subscription = subscriptionRepository.findSubscriptionByEmail(email);
        subscriptionRepository.delete(subscription.get());
        log.info(String.format("email %s  unSubscribed!", email));
    }

    private void sendRequestToKafka(Subscription subscription) {
        kafkaTemplate.send(KAFKA_TOPIC, subscription);
        log.info(String.format("Sent request to Kafka with data %s", subscription.toString()));
    }
}
