package com.thfp.clientservice.application.client;

import com.thfp.clientservice.application.client.dto.ClientCreateDTO;
import com.thfp.clientservice.application.client.dto.ClientResponseDTO;
import com.thfp.clientservice.application.client.dto.mapper.ClientMapper;
import com.thfp.clientservice.domain.cliente.Client;
import com.thfp.clientservice.domain.cliente.ClientService;
import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class clientsController {
    private clientsService clientService;
    private clientsMapper clientMapper;

    public clientsController(clientsService clientService, clientsMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<clientsResponseDTO> save(@RequestBody clientsCreateDTO clientCreateDTO){
        clients client = clientMapper.toEntity(clientCreateDTO);
        clients clientRecorded = clientService.save(client);
        clientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<clientsResponseDTO>> searchAll(){
        List<clients> clients = clientService.searchAll();
        List<clientsResponseDTO> clientResponseDTO = clientMapper.toDTO(clients);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> search(@PathVariable(value = "id") UUID id) throws clientsNotFoundException {
        clients clientRecorded = clientService.searchClientbyID(id);
        clientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid clientsCreateDTO clientCreateDTO) throws clientsNotFoundException{
        clients client = clientMapper.toEntity(clientCreateDTO);
        clients clientRecorded = clientService.changeClient(id, client);
        clientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delet(@PathVariable(value = "id") UUID id) throws clientsNotFoundException{
        clientService.deletClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
    }
}
