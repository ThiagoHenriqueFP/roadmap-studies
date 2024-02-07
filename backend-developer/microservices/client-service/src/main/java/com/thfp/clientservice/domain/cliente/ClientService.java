package com.example.crudclientes.domain.cliente;

import com.example.crudclientes.infrastructure.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public List<Client> searchAll(){
        return clientRepository.findAll();
    }

    public Client searchClientbyID(UUID id) throws ClientNotFoundException{
        Optional<Client> opt = clientRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientNotFoundException("Cliente com id: " + id + " não existe");
        }
    }

    public Client changeClient(UUID id, Client client) throws ClientNotFoundException{
        Client clientRecorded = searchClientbyID(id);
        clientRecorded.setEmail(client.getEmail());
        clientRecorded.setNome(client.getNome());
        return clientRepository.save(clientRecorded);
    }

    public void deletClient(UUID id) throws ClientNotFoundException{
        Client client = searchClientbyID(id);
        clientRepository.delete(client);
    }
}
