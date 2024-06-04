package com.example.MS3.mapper;


import com.example.MS3.dto.EndpointDTO;
import com.example.MS3.model.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EndpointMapper {

    Endpoint mapToEntity(EndpointDTO endpointDTO);


}