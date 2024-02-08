package com.thfp.clientservice.infrastructure.Global;


import com.thfp.clientservice.infrastructure.exception.ClientInfosNotFoundException;
import com.thfp.clientservice.infrastructure.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> ValidationExceptionHandling(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String attribute = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(attribute, message);
        });
        return errors;
    }

    public Map<String, String> clientNotFoundException(ClientNotFoundException ex){
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("mensagem", ex.getMessage());
        return errorsMap;
    }

    public Map<String, String> clientInfosNotFoundException(ClientInfosNotFoundException ex){
        Map<String, String> errorsMap = new HashMap<String, String>();
        errorsMap.put("mensagem", ex.getMessage());
        return errorsMap;
    }
}
