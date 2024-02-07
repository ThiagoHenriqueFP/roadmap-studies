package com.example.crudclientes.application.client;

import com.example.crudclientes.application.client.dto.ClientCreateDTO;
import com.example.crudclientes.application.client.dto.ClientResponseDTO;
import com.example.crudclientes.application.client.dto.mapper.ClientMapper;
import com.example.crudclientes.domain.cliente.Client;
import com.example.crudclientes.domain.cliente.ClientService;
import com.example.crudclientes.infrastructure.exception.ClientNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> save(@RequestBody ClientCreateDTO clientCreateDTO){
        Client client = clientMapper.toEntity(clientCreateDTO);
        Client clientRecorded = clientService.save(client);
        ClientResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> searchAll(){
        List<Client> clients = clientService.searchAll();
        List<ClientResponseDTO> clientResponseDTO = clientMapper.toDTO(clients);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> search(@PathVariable(value = "id") UUID id) throws ClientNotFoundException{
        Client clientRecorded = clientService.searchClientbyID(id);
        ClientResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> change(@PathVariable(value = "id")UUID id,
                                         @RequestBody @Valid ClientCreateDTO clientCreateDTO) throws ClientNotFoundException{
        Client client = clientMapper.toEntity(clientCreateDTO);
        Client clientRecorded = clientService.changeClient(id, client);
        ClientResponseDTO clientResponseDTO = clientMapper.toDTO(clientRecorded);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delet(@PathVariable(value = "id") UUID id) throws ClientNotFoundException{
        clientService.deletClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
    }
}
