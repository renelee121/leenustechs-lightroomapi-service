package com.mx.leenustechs.iotroomapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.leenustechs.iotroomapi.dto.GenericEventObjectDto;
import com.mx.leenustechs.iotroomapi.model.request.GenericEventObjectRequest;
import com.mx.leenustechs.iotroomapi.model.response.GenericEventObjectResponse;
import com.mx.leenustechs.iotroomapi.service.OperationTypeService;

import reactor.core.publisher.Mono;



@RestController
public class IOTRoomApiApplicationRestController {

    @Autowired
    private OperationTypeService operationTypeService;

    @PostMapping("/event/{type}")
    public Mono<GenericEventObjectResponse> handleEvent(@RequestBody GenericEventObjectRequest request) {
        GenericEventObjectDto event = new GenericEventObjectDto(request);
        
        return event.getOperationType().execute(event.toModel(), operationTypeService);
    }
}
