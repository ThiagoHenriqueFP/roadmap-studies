package com.example.crudclientes.application.client.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class ClientCreateDTO {

    @CPF
    private String cpf;

    @NotBlank(message = "O nome deve ser informado")
    private String nome;

    @NotBlank(message = "O email deve ser informado")
    @Size(min = 2, message = "O nome deve ter no mínimo 2 caracterers")
    private String email;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
