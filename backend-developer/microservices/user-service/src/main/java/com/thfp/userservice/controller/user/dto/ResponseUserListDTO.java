package com.thfp.userservice.controller.user.dto;

import java.util.List;

public record ResponseUserListDTO(
        List<ResponseUserDTO> user,
        Integer pageSize,
        Integer pageNumber,
        Boolean hasNext
) {
}
