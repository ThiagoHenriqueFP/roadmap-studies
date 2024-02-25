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
public class clientsMapper {

    @Autowired
    private ModelMapper mapper;

    public clients toEntity(ClientCreateDTO dto){
        clients entity = mapper.map(dto, Client.class);
        return entity;
    }

    public clientsResponseDTO toDTO(Client entity){
        clientsResponseDTO dto = mapper.map(entity, clientsResponseDTO.class);
        return dto;
    }

    public List<clientsResponseDTO> toDTO(List<Client> clients){
        return clients.stream()
                .map(client -> toDTO(client))
                .collect(Collectors.toList());
    }
}
