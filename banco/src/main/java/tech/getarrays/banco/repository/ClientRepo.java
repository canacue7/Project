package tech.getarrays.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.banco.entity.ClientEntity;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<ClientEntity, Long> {
    void deleteClientById(Long id);

    Optional<ClientEntity> findClientById(Long id);
}
