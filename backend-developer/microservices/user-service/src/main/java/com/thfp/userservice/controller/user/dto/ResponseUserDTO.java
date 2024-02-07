package com.thfp.userservice.controller.user.dto;

import java.time.LocalDateTime;

public record ResponseUserDTO(
        String name,
        String email,
        LocalDateTime createdAt
) {
}
