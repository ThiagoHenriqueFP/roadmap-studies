package com.thfp.clientservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(
                "que que ha velhinho?"
        );
    }
}
