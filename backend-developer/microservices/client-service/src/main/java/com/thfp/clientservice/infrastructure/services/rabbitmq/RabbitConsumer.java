package com.thfp.clientservice.infrastructure.services.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thfp.clientservice.domain.cliente.Client;
import com.thfp.clientservice.domain.cliente.ClientService;
import com.thfp.clientservice.domain.cliente_infos.ClientInfosService;
import com.thfp.clientservice.domain.rabbit.ResponseMessage;
import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    private final ClientInfosService clientInfosService;
    private final ClientService clientService;

    public RabbitConsumer(ClientInfosService clientInfosService, ClientService clientService) {
        this.clientInfosService = clientInfosService;
        this.clientService = clientService;
    }

    @RabbitListener(queues = "${queue.receiver.name}")
    public void listener(@Payload ResponseMessage body) throws ClientNotFoundException {

        Client client = this.clientService.searchByCPF(body.cpf());
        clientService.insertInfo(client, body);

        System.out.println(body);
    }
}
