package com.mx.leenustechs.iotroomapi.model.request;

import com.mx.leenustechs.iotroomapi.model.type.OperationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericEventObjectRequest{
    private OperationType operationType;
    public JsonNode payload;
}
