package com.thfp.clientservice.config.scheduler;

import com.thfp.clientservice.config.rabbitmq.RabbitProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyUpdateClientScheduler {

    private final RabbitProducer producer;

    public DailyUpdateClientScheduler(RabbitProducer producer) {
        this.producer = producer;
    }

    @Scheduled(cron = "0 * * * * *")
    private void dailyUpdateClientScheduler() {
        producer.send("{\"cpf\": \"01797501437\"}");
    }

}
