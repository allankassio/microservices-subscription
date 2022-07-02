package com.microservicesubscription.subscriptionservice.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 23:42
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Subscription implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    private String first_name;
    
    @NotBlank
    @Email
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;
    
    @NotNull
    private boolean consent;
    @ManyToOne
    @JoinColumn(name = "newsletter_id")
    private Newsletter newsletter;
}
