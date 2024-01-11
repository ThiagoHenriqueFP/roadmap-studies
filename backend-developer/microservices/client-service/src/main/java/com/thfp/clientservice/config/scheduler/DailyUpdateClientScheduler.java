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

    @Scheduled(cron = "* 1/2 * * * *")
    private void dailyUpdateClientScheduler() {
        producer.send(String.valueOf(System.currentTimeMillis()));
    }

}
