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
@RequestMapping("/clientesinfos")
public class clientsInfosController {

    private clientsInfosService clientInfosService;

    private clientsInfosMapper clientInfosMapper;

    public clientsInfosController(ClientInfosService clientInfosService, clientsInfosMapper clientInfosMapper) {
        this.clientInfosService = clientInfosService;
        this.clientInfosMapper = clientInfosMapper;
    }

    @PostMapping("/infos")
    public ResponseEntity<clientsInfosResponseDTO> save(@RequestBody clientsInfosCreateDTO clientInfosCreateDTO){
        clientsInfos clientInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        clientsInfos clientInfosRecorded = clientInfosService.save(clientInfos);
        clientsInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientInfosResponseDTO);
    }

    @GetMapping("/infos")
    public ResponseEntity<List<ClientInfosResponseDTO>> searchAll(){
        List<clientsInfos> clientsInfos = clientInfosService.searchAll();
        List<clientsInfosResponseDTO> clientInfosResponseDTO = clientInfosMapper.toDTO(clientsInfos);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @GetMapping("/infos/{id}")
    public ResponseEntity<Object> search(@PathVariable(value = "id")UUID id) throws clientsInfosNotFoundException {
        clientsInfos clientInfosRecorded = clientInfosService.searchClientInfosbyID(id);
        clientsInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @PutMapping("/infos/{id}")
    public ResponseEntity<Object> change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid clientsInfosCreateDTO clientInfosCreateDTO) throws clientsInfosNotFoundException{
        clientsInfos clientInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        clientsInfos clientInfosRecorded = clientInfosService.changeClientInfos(id, clientInfos);
        clientsInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @DeleteMapping("/infos/{id}")
    public ResponseEntity<Object> delet(@PathVariable(value = "id")UUID id) throws clientsInfosNotFoundException{
        clientInfosService.deletClientInfos(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
    }


}
