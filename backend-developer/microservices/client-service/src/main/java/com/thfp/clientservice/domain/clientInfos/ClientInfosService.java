package com.thfp.clientservice.domain.cliente_infos;

import com.thfp.clientservice.infrastructure.exception.ClientInfosNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientInfosService {
    private ClientInfosRepository clientInfosRepository;

    public ClientInfosService(ClientInfosRepository clientInfosRepository){
        this.clientInfosRepository = clientInfosRepository;
    }

    public ClientInfos Save(ClientInfos clientsInfos){
        return clientInfosRepository.save(clientsInfos);
    }

    public List<ClientInfos> SearchAll(){
        return clientInfosRepository.findAll();
    }

    public ClientInfos SearchClientInfosbyID(UUID id) throws ClientInfosNotFoundException {
        Optional<ClientInfos> opt = clientInfosRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientInfosNotFoundException("Informações do cliente com id: " + id + " não existe");
        }
    }
''
    public ClientInfos ChangeClientInfos(UUID id, ClientInfos clientsInfos) throws ClientInfosNotFoundException{
        ClientInfos clientInfosRecorded = searchClientInfosbyID(id);
        clientInfosRecorded.setDescription(clientsInfos.getDescription());
        clientInfosRecorded.setDateTime(clientsInfos.getDateTime());
        return clientInfosRepository.save(clientInfosRecorded);
    }

    public void DeletClientInfos(UUID id) throws ClientInfosNotFoundException{
        ClientInfos clientInfos = searchClientInfosbyID(id);
        clientInfosRepository.delete(clientInfos);
    }
}
