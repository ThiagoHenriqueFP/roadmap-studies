package com.thfp.userservice.controller.user.dto;

public record SaveUserDTO(
        String name,
        String email,
        String password
) {

}
