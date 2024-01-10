package com.thfp.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
public class OpsController {
    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(
                "Que que há velhinho!"
        );
    }
}
