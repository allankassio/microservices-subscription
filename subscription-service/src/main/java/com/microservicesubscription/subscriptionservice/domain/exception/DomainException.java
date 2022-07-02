package com.microservicesubscription.subscriptionservice.domain.exception;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 21:35
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
