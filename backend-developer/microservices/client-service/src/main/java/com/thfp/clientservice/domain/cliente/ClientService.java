package com.thfp.clientservice.domain.cliente;

import com.thfp.clientservice.domain.cliente_infos.ClientInfos;
import com.thfp.clientservice.domain.cliente_infos.ClientInfosService;
import com.thfp.clientservice.domain.rabbit.ResponseMessage;
import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import jakarta.ws.rs.ClientErrorException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private final ClientInfosService clientInfosService;
    public ClientService(ClientRepository clientRepository, ClientInfosService clientInfosService) {
        this.clientRepository = clientRepository;
        this.clientInfosService = clientInfosService;
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

    public Client searchByCPF(String cpf) throws ClientNotFoundException {
        return this.clientRepository
                .findByCpf(cpf)
                .orElseThrow(() ->
                        new ClientNotFoundException("Cliente com cpf: " + cpf + " não existe")
                );
    }

    public void insertInfo(Client client, ResponseMessage info) {

        // Fecth lazily threw a exception because has a null pointer
        // "how do add a data from a not know array?"
        client.setClientInfos(
                this.clientRepository.findInfosById(client.getId())
        );
        // cascade saving
        // let spring jpa handle the relationship between classes
        client.getClientInfos().add(new ClientInfos(
                info.date(),
                info.description()
        ));

        this.save(client);
    }
}
