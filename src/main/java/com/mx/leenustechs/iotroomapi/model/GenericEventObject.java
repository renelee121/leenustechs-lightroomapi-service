package com.mx.leenustechs.iotroomapi.model;

import java.util.UUID;

import com.mx.leenustechs.iotroomapi.model.type.OperationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericEventObject implements Event {
    private UUID transactionId;
    private OperationType operationType;
    private JsonNode payload;
}
