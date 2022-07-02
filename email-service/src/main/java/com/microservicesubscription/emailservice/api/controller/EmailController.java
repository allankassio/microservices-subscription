package com.microservicesubscription.emailservice.api.controller;

import com.microservicesubscription.emailservice.domain.repository.EmailRepository;
import com.microservicesubscription.emailservice.domain.service.EmailService;
import com.microservicesubscription.emailservice.model.Email;
import com.microservicesubscription.emailservice.model.Status;
import com.microservicesubscription.emailservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 07:23
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/emails")
public class EmailController {
    
    private final EmailRepository emailRepository;
    private final EmailService emailService;

    @GetMapping("/{status}")
    public List<Email> getAllEmailsByStatus(@PathVariable String status) {
        log.info("Returns all emails from status filtering.");
        return emailRepository.findAllByStatus(Status.valueOf(status.toUpperCase()));
    }

    @GetMapping
    public List<Email> getAllEmails() {
        log.info("Returns all emails.");
        return emailRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Email sendingEmail(@Valid @RequestBody Subscription subscription) {
        return emailService.sendEmail(subscription);
    }


}
