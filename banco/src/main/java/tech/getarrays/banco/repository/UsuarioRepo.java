package tech.getarrays.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.banco.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<UsuarioEntity, Long> {
    void deleteUsuarioById(Long id);

    Optional<UsuarioEntity> findUsuarioById(Long id);
}
