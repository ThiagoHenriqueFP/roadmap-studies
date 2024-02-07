package com.thfp.clientservice.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProducer {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public RabbitProducer(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    public void send(String message) {
        template.convertAndSend(this.queue.getName(), message);
        System.out.println("send " + message);
    }
}
