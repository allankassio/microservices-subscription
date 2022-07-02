package com.microservicesubscription.publicservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/07/22 15:32
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {
    private String first_name;
    @NotBlank
    @Email
    private String email;
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
    @NotNull
    private Date date_of_birth;
    @NotNull
    private boolean consent;
    @NotNull
    private UUID newsletter_id;
}
