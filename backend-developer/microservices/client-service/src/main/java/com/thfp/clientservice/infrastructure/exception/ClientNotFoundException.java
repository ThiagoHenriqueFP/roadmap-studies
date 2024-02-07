package com.example.crudclientes.infrastructure.exception;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(String message){
        super(message);
    }
}
