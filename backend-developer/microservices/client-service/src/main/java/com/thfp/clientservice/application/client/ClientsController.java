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
@RequestMapping("/clients")
public class ClientsController {
    private ClientsService clientService;
    private ClientsMapper clientMapper;

    public ClientsController(ClientsService clientService, ClientsMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientsResponseDTO> Save(@RequestBody ClientsCreateDTO clientCreateDTO){
        Clients client = clientMapper.toEntity(clientCreateDTO);
        Clients clientRecorded = clientService.Save(client);
        ClientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientsResponseDTO>> SearchAll(){
        List<Clients> clients = clientService.SearchAll();
        List<ClientsResponseDTO> clientResponseDTO = clientMapper.toDTO(clients);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> Search(@PathVariable(value = "id") UUID id) throws ClientsNotFoundException {
        Clients clientRecorded = clientService.SearchClientbyID(id);
        ClientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> Change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid ClientsCreateDTO clientCreateDTO) throws ClientsNotFoundException{
        Clients client = clientMapper.toEntity(clientCreateDTO);
        Clients clientRecorded = clientService.ChangeClient(id, client);
        ClientsResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delet(@PathVariable(value = "id") UUID id) throws ClientsNotFoundException{
        clientService.DeletClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
    }
}
