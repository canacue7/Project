package tech.getarrays.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.getarrays.banco.entity.OperacionesEntity;

import java.util.List;

public interface OperacionesRepo extends JpaRepository<OperacionesEntity, Long> {

    @Query(value="select * from operaciones_entity where (id_cuenta=?)", nativeQuery = true)
    List<OperacionesEntity> findOpsByIdCuenta(Long idCuenta);
}
