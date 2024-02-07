package com.example.crudclientes.application.client.dto.mapper;

import com.example.crudclientes.domain.cliente.Client;
import com.example.crudclientes.application.client.dto.ClientCreateDTO;
import com.example.crudclientes.application.client.dto.ClientResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private ModelMapper mapper;

    public Client toEntity(ClientCreateDTO dto){
        Client entity = mapper.map(dto, Client.class);
        return entity;
    }

    public ClientResponseDTO toDTO(Client entity){
        ClientResponseDTO dto = mapper.map(entity, ClientResponseDTO.class);
        return dto;
    }

    public List<ClientResponseDTO> toDTO(List<Client> clients){
        return clients.stream()
                .map(client -> toDTO(client))
                .collect(Collectors.toList());
    }
}
