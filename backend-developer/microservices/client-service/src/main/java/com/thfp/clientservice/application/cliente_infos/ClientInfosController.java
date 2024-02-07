package com.example.crudclientes.application.cliente_infos;

import com.example.crudclientes.application.client.dto.ClientResponseDTO;
import com.example.crudclientes.application.cliente_infos.dto.ClientInfosCreateDTO;
import com.example.crudclientes.application.cliente_infos.dto.ClientInfosResponseDTO;
import com.example.crudclientes.application.cliente_infos.dto.mapper.ClientInfosMapper;
import com.example.crudclientes.domain.cliente_infos.ClientInfos;
import com.example.crudclientes.domain.cliente_infos.ClientInfosService;
import com.example.crudclientes.infrastructure.exception.ClientInfosNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientesinfos")
public class ClientInfosController {

    private ClientInfosService clientInfosService;

    private ClientInfosMapper clientInfosMapper;

    public ClientInfosController(ClientInfosService clientInfosService, ClientInfosMapper clientInfosMapper) {
        this.clientInfosService = clientInfosService;
        this.clientInfosMapper = clientInfosMapper;
    }

    @PostMapping
    public ResponseEntity<ClientInfosResponseDTO> save(@RequestBody ClientInfosCreateDTO clientInfosCreateDTO){
        ClientInfos clientInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        ClientInfos clientInfosRecorded = clientInfosService.save(clientInfos);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientInfosResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientInfosResponseDTO>> searchAll(){
        List<ClientInfos> clientsInfos = clientInfosService.searchAll();
        List<ClientInfosResponseDTO> clientInfosResponseDTO = clientInfosMapper.toDTO(clientsInfos);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> search(@PathVariable(value = "id")UUID id) throws ClientInfosNotFoundException{
        ClientInfos clientInfosRecorded = clientInfosService.searchClientInfosbyID(id);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid ClientInfosCreateDTO clientInfosCreateDTO) throws ClientInfosNotFoundException{
        ClientInfos clientInfos = clientInfosMapper.toEntity(clientInfosCreateDTO);
        ClientInfos clientInfosRecorded = clientInfosService.changeClientInfos(id, clientInfos);
        ClientInfosResponseDTO clientInfosResponseDTO = clientInfosMapper.toDTO(clientInfosRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientInfosResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delet(@PathVariable(value = "id")UUID id) throws ClientInfosNotFoundException{
        clientInfosService.deletClientInfos(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
    }


}
