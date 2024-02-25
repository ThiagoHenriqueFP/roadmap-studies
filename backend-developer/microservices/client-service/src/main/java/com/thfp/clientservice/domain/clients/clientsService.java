package com.thfp.clientservice.domain.cliente;

import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class clientsService {
    private clientsRepository clientRepository;

    public clientsService(clientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public clients save(clients client){
        return clientRepository.save(client);
    }

    public List<clients> searchAll(){
        return clientRepository.findAll();
    }

    public clients searchClientbyID(UUID id) throws ClientNotFoundException{
        Optional<clients> opt = clientsRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientNotFoundException("Cliente com id: " + id + " não existe");
        }
    }

    public clients changeClient(UUID id, clients client) throws clientsNotFoundException{
        clients clientRecorded = searchClientbyID(id);
        clientRecorded.setEmail(client.getEmail());
        clientRecorded.setNome(client.getNome());
        return clientsRepository.save(clientRecorded);
    }

    public void deletClient(UUID id) throws clientsNotFoundException{
        clients client = searchClientbyID(id);
        clientsRepository.delete(client);
    }
}
