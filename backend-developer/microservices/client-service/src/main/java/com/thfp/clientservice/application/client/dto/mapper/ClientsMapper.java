package com.thfp.clientservice.application.client.dto.mapper;

import com.thfp.clientservice.application.client.dto.ClientCreateDTO;
import com.thfp.clientservice.application.client.dto.ClientResponseDTO;
import com.thfp.clientservice.domain.cliente.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientsMapper {

    @Autowired
    private ModelMapper mapper;

    public Clients toEntity(ClientsCreateDTO dto){
        Clients entity = mapper.map(dto, Clients.class);
        return entity;
    }

    public ClientsResponseDTO toDTO(Clients entity){
        ClientsResponseDTO dto = mapper.map(entity, ClientsResponseDTO.class);
        return dto;
    }

    public List<ClientsResponseDTO> toDTO(List<Clients> clients){
        return clients.stream()
                .map(client -> toDTO(client))
                .collect(Collectors.toList());
    }
}
