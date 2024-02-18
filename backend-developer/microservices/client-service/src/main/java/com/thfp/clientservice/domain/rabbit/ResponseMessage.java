package com.thfp.clientservice.domain.rabbit;

import java.time.LocalDateTime;
// dto to receive data from rabbitmq broker
public record ResponseMessage(
        String cpf,
        LocalDateTime date,
        String description
) {
}
