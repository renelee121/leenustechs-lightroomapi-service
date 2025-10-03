package com.mx.leenustechs.iotroomapi.service.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import com.mx.leenustechs.iotroomapi.bussines.utils.client.RestClientService;
import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;
import com.mx.leenustechs.iotroomapi.service.SyncManagerService;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import tools.jackson.databind.ObjectMapper;

@Service
public class SyncManagerServiceImpl implements SyncManagerService{

    @Autowired
    private RestClientService restClientService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<GenericEventObjectResponse> consultReactive(String transactionId) {
        return restClientService.getDataFromService(transactionId)
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, GenericEventObjectResponse.class);
                    } catch (JsonParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .retryWhen(Retry.fixedDelay(10, Duration.ofMillis(500))
                        .filter(e -> e instanceof RuntimeException
                                && e.getMessage() != null
                                && e.getMessage().startsWith("Error en GET")));
    }

}
