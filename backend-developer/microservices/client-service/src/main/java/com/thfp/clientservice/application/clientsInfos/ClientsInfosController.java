package com.thfp.clientservice.application.cliente_infos;


import com.thfp.clientservice.application.cliente_infos.dto.ClientInfosCreateDTO;
import com.thfp.clientservice.application.cliente_infos.dto.ClientInfosResponseDTO;
import com.thfp.clientservice.application.cliente_infos.dto.mapper.ClientInfosMapper;
import com.thfp.clientservice.domain.cliente_infos.ClientInfos;
import com.thfp.clientservice.domain.cliente_infos.ClientInfosService;
import com.thfp.clientservice.infrastructure.exception.ClientInfosNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients/infos")
public class ClientInfosController {

    private ClientInfosService clientInfosService;

    private ClientInfosMapper clientInfosMapper;

    public ClientInfosController(ClientInfosService clientInfosService, ClientInfosMapper clientInfosMapper) {
        this.clientInfosService = clientInfosService;
        this.clientInfosMapper = clientInfosMapper;
    }

    @PostMapping
    public ResponseEntity<ClientInfosResponseDTO> Save(@RequestBody ClientInfosCreateDTO clientInfosCreateDTO){
        ClientInfos clientsInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        ClientInfos clientInfosRecorded = clientInfosService.Save(clientsInfos);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientInfosResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientInfosResponseDTO>> SearchAll(){
        List<ClientInfos> clientsInfos = clientInfosService.SearchAll();
        List<ClientInfosResponseDTO> clientInfosResponseDTO = clientInfosMapper.toDTO(clientsInfos);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> Search(@PathVariable(value = "id")UUID id) throws ClientInfosNotFoundException {
        ClientInfos clientInfosRecorded = clientInfosService.SearchClientInfosbyID(id);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> Change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid ClientInfosCreateDTO clientInfosCreateDTO) throws ClientInfosNotFoundException{
        ClientInfos clientsInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        ClientInfos clientInfosRecorded = clientInfosService.ChangeClientInfos(id, clientsInfos);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id")UUID id) throws ClientInfosNotFoundException{
        clientInfosService.DeletClientInfos(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
    }
