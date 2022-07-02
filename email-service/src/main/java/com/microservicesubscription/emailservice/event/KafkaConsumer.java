package com.microservicesubscription.emailservice.event;

import com.microservicesubscription.emailservice.domain.service.EmailService;
import com.microservicesubscription.emailservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 12:22
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final String KAFKA_TOPIC = "subscription-system-subscribe-successful";
    private final String KAFKA_GROUP_ID = "group_id";
    private final EmailService emailService;

    @KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_GROUP_ID)
    public void consumer(Subscription subscription) {
        log.info("Consuming information from Kafka");
        emailService.sendEmail(subscription);
    }
}
