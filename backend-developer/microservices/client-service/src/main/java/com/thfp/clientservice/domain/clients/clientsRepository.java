package com.thfp.clientservice.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface clientsRepository extends JpaRepository<clients, UUID>  {
}
