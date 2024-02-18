package com.thfp.clientservice.infrastructure.config.initialization;

import com.thfp.clientservice.domain.cliente.Client;
import com.thfp.clientservice.domain.cliente.ClientService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialData implements ApplicationRunner {

    private final ClientService clientService;

    public InitialData(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Client c1 = new Client(
                "34398282041",
                "Fulano da Silva",
                "emailDaSilva@mail.com"
        );
        Client c2 = new Client(
                "08551869094",
                "Beltrano da Costa",
                "emailDaCosta@mail.com"
        );
        Client c3 = new Client(
                "95880105008",
                "Ciclano Medeiros",
                "emailMedeiros@mail.com"
        );

        clientService.save(c1);
        clientService.save(c2);
        clientService.save(c3);
    }
}
