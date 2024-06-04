package com.example.MS3.batch.mapper;

import com.example.MS3.batch.d.EndpointDTO;
import com.example.MS3.batch.model.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface EndpointInfoMapper {

        Endpoint mapToEntity(EndpointDTO endpointDTO);
    }
