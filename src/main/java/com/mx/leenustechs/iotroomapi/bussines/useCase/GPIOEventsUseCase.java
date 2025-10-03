package com.mx.leenustechs.iotroomapi.bussines.useCase;

import org.springframework.stereotype.Component;

import com.mx.leenustechs.iotroomapi.bussines.EventOperation;
import com.mx.leenustechs.iotroomapi.dto.GenericEventObjectDto;
import com.mx.leenustechs.iotroomapi.model.GenericEventObject;
import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;
import com.mx.leenustechs.iotroomapi.service.KafkaPublisherService;
import com.mx.leenustechs.iotroomapi.service.SyncManagerService;

import reactor.core.publisher.Mono;

@Component
public class GPIOEventsUseCase implements EventOperation {

    private KafkaPublisherService producerService;
    private SyncManagerService syncManagerService;

    public GPIOEventsUseCase(KafkaPublisherService producerService,SyncManagerService syncManagerService){
        this.producerService = producerService;
        this.syncManagerService = syncManagerService;
    }

    @Override
    public Mono<GenericEventObjectResponse> execute(GenericEventObject event) {
        GenericEventObjectDto dto = new GenericEventObjectDto(event);
        producerService.send(dto.getTransactionId().toString(), dto.toModel());
        return  syncManagerService.consultReactive(dto.getTransactionId().toString());
    }
}
