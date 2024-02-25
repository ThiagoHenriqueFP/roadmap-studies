package com.thfp.clientservice.application.cliente_infos.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class clientsInfosCreateDTO {

    @NotBlank(message = "a data deve ser informada")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate DateTime;
    private String descripiton;

    public LocalDate getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        DateTime = dateTime;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }
}
