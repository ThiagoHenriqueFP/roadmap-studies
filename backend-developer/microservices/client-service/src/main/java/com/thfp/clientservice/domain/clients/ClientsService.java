package com.thfp.clientservice.domain.cliente;

import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientsService {
    private ClientsRepository clientRepository;

    public ClientsService(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Clients Save(Clients client){
        return clientRepository.save(client);
    }

    public List<Clients> SearchAll(){
        return clientRepository.findAll();
    }

    public Clients SearchClientbyID(UUID id) throws ClientsNotFoundException{
        Optional<Clients> opt = clientRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientsNotFoundException("Cliente com id: " + id + " não existe");
        }
    }

    public Clients ChangeClient(UUID id, Clients client) throws ClientsNotFoundException{
        Clients clientRecorded = searchClientbyID(id);
        clientRecorded.setEmail(client.getEmail());
        clientRecorded.setNome(client.getNome());
        return clientsRepository.save(clientRecorded);
    }

    public void DeletClient(UUID id) throws ClientsNotFoundException{
        Clients client = searchClientbyID(id);
        clientsRepository.delete(client);
    }
}
