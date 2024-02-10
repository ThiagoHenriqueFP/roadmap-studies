package com.thfp.clientservice.domain.cliente;

import com.thfp.clientservice.domain.cliente_infos.ClientInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID>  {
    Optional<Client> findByCpf(String cpf);

    // custom query to resolve lazy fetch problem
    @Query("SELECT c.clientInfos FROM Client c WHERE c.id = :id")
    List<ClientInfos> findInfosById(UUID id);
}
