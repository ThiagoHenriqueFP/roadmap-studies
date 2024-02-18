package com.thfp.clientservice.infrastructure.config.scheduler;

import com.thfp.clientservice.domain.cliente.Client;
import com.thfp.clientservice.domain.cliente.ClientService;
import com.thfp.clientservice.domain.rabbit.SendMessage;
import com.thfp.clientservice.infrastructure.services.rabbitmq.RabbitProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyUpdateClientScheduler {

    private final RabbitProducer producer;
    private final ClientService clientService;

    public DailyUpdateClientScheduler(RabbitProducer producer, ClientService clientService) {
        this.producer = producer;
        this.clientService = clientService;
    }
    // cron job to update clients
    @Scheduled(cron = "0 * * * * *")
    private void dailyUpdateClientScheduler() {
        List<Client> clientList = this.clientService.searchAll();
        // iterate the client list and send messages to broker
        clientList.forEach(it ->
            producer.send(new SendMessage(it.getCpf()))
        );
    }

}
