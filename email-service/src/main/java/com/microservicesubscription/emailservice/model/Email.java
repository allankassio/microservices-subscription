package com.microservicesubscription.emailservice.model;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 06:12
 */
@Data
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String email_to;

    private LocalDateTime send_date;
}
