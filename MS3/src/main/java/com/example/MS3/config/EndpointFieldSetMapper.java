package com.example.MS3.config;

import com.example.MS3.model.Endpoint;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndpointFieldSetMapper implements FieldSetMapper<Endpoint> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public Endpoint mapFieldSet(FieldSet fieldSet) throws BindException { // per mappare i campi del file CSV
        Endpoint endpoint = new Endpoint();
        endpoint.setEndpointId(fieldSet.readLong("endpointId"));
        endpoint.setEndpointPath(fieldSet.readString("endpointPath"));
        endpoint.setEndpointType(fieldSet.readString("endpointType"));

        // Converti la stringa in LocalDateTime
        String dateString = fieldSet.readString("endpointDate"); // Per leggere il campo endpointDate in formato yyyy-MM-dd'T'HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        endpoint.setEndpointDate(dateTime);

        return endpoint;
    }
}
