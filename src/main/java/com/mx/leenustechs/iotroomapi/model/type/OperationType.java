package com.mx.leenustechs.iotroomapi.model.type;

import com.mx.leenustechs.iotroomapi.bussines.EventOperation;
import com.mx.leenustechs.iotroomapi.model.GenericEventObject;
import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;
import com.mx.leenustechs.iotroomapi.service.OperationTypeService;

import reactor.core.publisher.Mono;

public enum OperationType {
    INPUT,
    OUTPUT;
    
    public Mono<GenericEventObjectResponse> execute(GenericEventObject GenericEventObject, OperationTypeService operationTypeService){
        EventOperation operation = operationTypeService.getOperation(this);
        return operation.execute(GenericEventObject);
    }
}
