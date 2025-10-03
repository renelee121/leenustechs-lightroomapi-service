package com.mx.leenustechs.iotroomapi.bussines;

import org.springframework.stereotype.Service;

import com.mx.leenustechs.iotroomapi.model.GenericEventObject;
import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;

import reactor.core.publisher.Mono;


@Service
public interface EventOperation {
    Mono<GenericEventObjectResponse> execute(GenericEventObject event);
}