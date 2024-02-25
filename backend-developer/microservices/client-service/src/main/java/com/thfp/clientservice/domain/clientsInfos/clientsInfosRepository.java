package com.thfp.clientservice.domain.cliente_infos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface clientsInfosRepository extends JpaRepository<clientsInfos, UUID> {
}
