package com.thfp.clientservice.application.cliente_infos.dto.mapper;


import com.thfp.clientservice.application.cliente_infos.dto.ClientInfosCreateDTO;
import com.thfp.clientservice.application.cliente_infos.dto.ClientInfosResponseDTO;
import com.thfp.clientservice.domain.cliente_infos.ClientInfos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class clientsInfosMapper {

    @Autowired
    private modelMapper mapper;

    public clientsInfos toEntity(clientsInfosCreateDTO dto){
        clientsInfos entity = mapper.map(dto, ClientInfos.class);
        return entity;
    }

    public clientsInfosResponseDTO toDTO(clientsInfos entity){
        clientsInfosResponseDTO dto = mapper.map(entity, clientsInfosResponseDTO.class);
        return dto;
    }

    public List<clientsInfosResponseDTO> toDTO(List<clientsInfos> clientInfos){
        return clientInfos.stream()
                .map(clientsInfos -> toDTO(clientsInfos))
                .collect(Collectors.toList());
    }
}
