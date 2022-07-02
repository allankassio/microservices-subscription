package com.microservicesubscription.emailservice.domain.repository;

import com.microservicesubscription.emailservice.model.Email;
import com.microservicesubscription.emailservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 30/06/22 07:36
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findAllByStatus(Status status);
}
