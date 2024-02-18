package com.thfp.clientservice.domain.rabbit;

// dto to send data to rabbitmq broker
public record SendMessage(
        String cpf
) {

}
