package com.thfp.clientservice.domain.cliente_infos;

import com.thfp.clientservice.domain.cliente.Client;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "client_info")
public class ClientInfos {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //
    // Client <---> ClientInfos
    // Client ----> ClientInfos
//    @ManyToOne
//    private Client clientId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;

    private String description;

    public ClientInfos() {}

    public LocalDateTime getDateTime() {
        return date;
    }

    public void setDateTime(LocalDateTime dateTime) {
        date = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    public Client getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(Client clientId) {
//        this.clientId = clientId;
//    }


    // constructor to facilitate create new instances
    public ClientInfos(LocalDateTime dateTime, String description) {
        this.date = dateTime;
        this.description = description;
    }
}
