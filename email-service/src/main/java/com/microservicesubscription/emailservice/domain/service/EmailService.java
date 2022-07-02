package com.microservicesubscription.emailservice.domain.service;

import com.microservicesubscription.emailservice.model.Subscription;
import com.microservicesubscription.emailservice.model.Email;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 07:52
 */
public interface EmailService {
    Email sendEmail(Subscription subscription);
}
