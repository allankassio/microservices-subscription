package com.microservicesubscription.emailservice.model;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 18:27
 */
@Data
public class Subscription implements Serializable {

    private String newsletter_id;

    private String first_name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    private String gender;

    @NotNull
    private boolean consent;

   

    
    
    
}
