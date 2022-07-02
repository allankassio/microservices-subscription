package com.microservicesubscription.emailservice.domain.service;

import com.microservicesubscription.emailservice.domain.repository.EmailRepository;
import com.microservicesubscription.emailservice.model.Subscription;
import com.microservicesubscription.emailservice.model.Email;
import com.microservicesubscription.emailservice.model.Status;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 12:13
 */
@Slf4j
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Override
    public Email sendEmail(Subscription subscription) {

        var email = new Email();
        email.setEmail_to(subscription.getEmail());
        email.setSend_date(LocalDateTime.now());

        try {
            var message = new SimpleMailMessage();
            message.setFrom("Here is the email of the sender");
            message.setTo(email.getEmail_to());
            message.setSubject("Here is the subject of the email");
            message.setText("Here is the body of the email with all the information");

            //Needs a function to send a real email here (like a send() )

            email.setStatus(Status.SENT);

            log.info(this.getClass().getName(), "Success! Email sent!.");

        } catch (MailException e) {
            email.setStatus(Status.ERROR);
            log.error("Error sending the e-mail: ", e);
        } finally {
            log.info("Saving email in the database.");
            email = emailRepository.save(email);
        }
        return email;
    }
}
