package com.mx.leenustechs.iotroomapi.service;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mx.leenustechs.iotroomapi.bussines.EventOperation;
import com.mx.leenustechs.iotroomapi.bussines.useCase.*;
import com.mx.leenustechs.iotroomapi.model.type.OperationType;

@Service
public class OperationTypeService {

    private Map<OperationType, EventOperation> operationMap = new EnumMap<>(OperationType.class);

    public OperationTypeService(GPIOEventsUseCase event) {
        operationMap.put(OperationType.INPUT, event);
        operationMap.put(OperationType.OUTPUT, event);
    }

    public EventOperation getOperation(OperationType operationType){
        return operationMap.get(operationType);
    }
}
