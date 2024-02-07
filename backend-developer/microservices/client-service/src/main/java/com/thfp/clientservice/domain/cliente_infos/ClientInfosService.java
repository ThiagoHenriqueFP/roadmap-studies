package com.example.crudclientes.domain.cliente_infos;

import com.example.crudclientes.infrastructure.exception.ClientInfosNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientInfosService {
    private ClientInfosRepository clientInfosRepository;

    public ClientInfosService(ClientInfosRepository clientInfosRepository){
        this.clientInfosRepository = clientInfosRepository;
    }

    public ClientInfos save(ClientInfos clientInfos){
        return clientInfosRepository.save(clientInfos);
    }

    public List<ClientInfos> searchAll(){
        return clientInfosRepository.findAll();
    }

    public ClientInfos searchClientInfosbyID(UUID id) throws ClientInfosNotFoundException{
        Optional<ClientInfos> opt = clientInfosRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientInfosNotFoundException("Informações do cliente com id: " + id + " não existe");
        }
    }

    public ClientInfos changeClientInfos(UUID id, ClientInfos clientInfos) throws ClientInfosNotFoundException{
        ClientInfos clientInfosRecorded = searchClientInfosbyID(id);
        clientInfosRecorded.setDescription(clientInfos.getDescription());
        clientInfosRecorded.setDateTime(clientInfos.getDateTime());
        return clientInfosRepository.save(clientInfosRecorded);
    }

    public void deletClientInfos(UUID id) throws ClientInfosNotFoundException{
        ClientInfos clientInfos = searchClientInfosbyID(id);
        clientInfosRepository.delete(clientInfos);
    }
}
