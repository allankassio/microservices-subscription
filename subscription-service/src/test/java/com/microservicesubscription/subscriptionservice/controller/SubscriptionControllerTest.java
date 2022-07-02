package com.microservicesubscription.subscriptionservice.controller;

import com.microservicesubscription.subscriptionservice.domain.repository.SubscriptionRepository;
import com.microservicesubscription.subscriptionservice.model.Newsletter;
import com.microservicesubscription.subscriptionservice.model.Subscription;
import com.microservicesubscription.subscriptionservice.model.dto.SubscriptionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.UUID;

/**
 * @author Allan Cruz (https://github.com/allankassio)
 * @version 1.0
 * @date 01/07/22 01:21
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SubscriptionRepository repository;

    private final String URL = "/v1/subscribers";

    @Test
    @WithMockUser(username = "teste", password = "u5u4r10t35t3", roles = {"ADMIN"})
    public void getAllSubscriptionsTest() throws Exception {
        var subscription = new Subscription();
        var newsletter = new Newsletter();
        newsletter.setId(UUID.fromString("698dc19d-489c-4e4d-b73e-28a713eab07b"));

        subscription.setEmail("teste@teste.com");
        subscription.setConsent(true);
        subscription.setDate_of_birth(new Date());
        subscription.setNewsletter(newsletter);

        repository.save(subscription);

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


}
