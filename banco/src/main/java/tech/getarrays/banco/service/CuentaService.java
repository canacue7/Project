package tech.getarrays.banco.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.repository.CuentaRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface CuentaService {

    public List<CuentaEntity> findAllCuentas();

    public List<CuentaEntity> findCuentabyIdClient(Long id) throws Exception;


    public CuentaEntity addCuenta (CuentaEntity cuenta);

    public CuentaEntity updateCuenta (CuentaEntity cuenta) throws Exception;

    public CuentaEntity findCuentabyId (Long id) throws Exception;

    public void deleteCuenta(Long id) throws Exception;


}
