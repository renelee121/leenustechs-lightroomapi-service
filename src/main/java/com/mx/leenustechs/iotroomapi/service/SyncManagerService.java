package com.mx.leenustechs.iotroomapi.service;

import org.springframework.stereotype.Service;

import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;

import reactor.core.publisher.Mono;

@Service
public interface SyncManagerService {
    public Mono<GenericEventObjectResponse> consultReactive(String transactionId);
}
