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
public class ClientInfosMapper {

    @Autowired
    private ModelMapper mapper;

    public ClientInfos toEntity(ClientInfosCreateDTO dto){
        ClientInfos entity = mapper.map(dto, ClientInfos.class);
        return entity;
    }

    public ClientInfosResponseDTO toDTO(ClientInfos entity){
        ClientInfosResponseDTO dto = mapper.map(entity, ClientInfosResponseDTO.class);
        return dto;
    }

    public List<ClientInfosResponseDTO> toDTO(List<ClientInfos> clientsInfos){
        return clientsInfos.stream()
                .map(clientInfos -> toDTO(clientInfos))
                .collect(Collectors.toList());
    }
}
