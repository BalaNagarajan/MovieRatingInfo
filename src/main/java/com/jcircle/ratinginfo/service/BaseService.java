package com.jcircle.ratinginfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Component
public class BaseService {

    private RestTemplate restTemplate;

    // Timeouts are in Milliseconds ( 5 seconds )
    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 5000;

    @Autowired
    public BaseService(RestTemplateBuilder restTemplateBuilder) {

            restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofMillis(CONNECTION_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(READ_TIMEOUT)).build();



    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
