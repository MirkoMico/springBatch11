package com.example.MS3.config;

import com.example.MS3.model.Endpoint;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndpointFieldSetMapper implements FieldSetMapper<Endpoint> {

    @Override
    public Endpoint mapFieldSet(FieldSet fieldSet) throws BindException { // per mappare i campi del file CSV
        Endpoint endpoint = new Endpoint();
        endpoint.setEndpointId(fieldSet.readLong("endpointId"));
        endpoint.setEndpointPath(fieldSet.readString("endpointPath"));
        endpoint.setEndpointType(fieldSet.readString("endpointType"));
        return endpoint;
    }
}
