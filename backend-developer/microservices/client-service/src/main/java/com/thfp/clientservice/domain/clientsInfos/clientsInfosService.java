package com.thfp.clientservice.domain.cliente_infos;

import com.thfp.clientservice.infrastructure.exception.ClientInfosNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class clientsInfosService {
    private clientsInfosRepository clientInfosRepository;

    public clientsInfosService(clientsInfosRepository clientInfosRepository){
        this.clientInfosRepository = clientInfosRepository;
    }

    public clientsInfos save(clientsInfos clientInfos){
        return clientInfosRepository.save(clientInfos);
    }

    public List<clientsInfos> searchAll(){
        return clientInfosRepository.findAll();
    }

    public clientsInfos searchClientInfosbyID(UUID id) throws ClientInfosNotFoundException {
        Optional<ClientInfos> opt = clientInfosRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new ClientInfosNotFoundException("Informações do cliente com id: " + id + " não existe");
        }
    }

    public clientsInfos changeClientInfos(UUID id, clientsInfos clientInfos) throws ClientInfosNotFoundException{
        clientsInfos clientInfosRecorded = searchClientInfosbyID(id);
        clientInfosRecorded.setDescription(clientInfos.getDescription());
        clientInfosRecorded.setDateTime(clientInfos.getDateTime());
        return clientInfosRepository.save(clientInfosRecorded);
    }

    public void deletClientInfos(UUID id) throws ClientInfosNotFoundException{
        clientsInfos clientInfos = searchClientInfosbyID(id);
        clientInfosRepository.delete(clientInfos);
    }
}
