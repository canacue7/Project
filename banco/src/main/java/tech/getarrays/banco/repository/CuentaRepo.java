package tech.getarrays.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.getarrays.banco.entity.CuentaEntity;

import java.util.List;

public interface CuentaRepo extends JpaRepository<CuentaEntity, Long> {


   @Query(value="select * from cuenta_entity where (id_usuario=?)", nativeQuery = true)
   List<CuentaEntity> findCuentaByIdClient(Long num_cuenta);

 /*  @Query(value="SELECT CURDATE()", nativeQuery = true)
   public cuenta getFecha(Date fecha_transfer);*/


   CuentaEntity findCuentaById(Long num_cuenta);

   void deleteCuentaById(Long id);


    /*
    void deleteCuentaoById(Long num_cuenta);

    Optional<Cuenta> findCuentaById(Long num_cuenta);

     */
}


