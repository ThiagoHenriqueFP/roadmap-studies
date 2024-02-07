package com.example.crudclientes.domain.cliente_infos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class ClientInfos {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate DateTime;

    private String description;

    private UUID client_id;

    public LocalDate getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        DateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }
}
