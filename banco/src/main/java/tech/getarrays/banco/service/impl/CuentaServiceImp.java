package tech.getarrays.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.repository.CuentaRepo;
import tech.getarrays.banco.service.CuentaService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CuentaServiceImp implements CuentaService {

    private final CuentaRepo cuentaRepo;

    @Autowired
    public CuentaServiceImp(CuentaRepo cuentaRepo){this.cuentaRepo= cuentaRepo; }

    public List<CuentaEntity> findAllCuentas(){
        return cuentaRepo.findAll();
    }

    public List<CuentaEntity> findCuentabyIdClient(Long id) throws Exception{return cuentaRepo.findCuentaByIdClient(id);}


    public CuentaEntity addCuenta (CuentaEntity cuenta){
        return cuentaRepo.save(cuenta);
    }

    public CuentaEntity updateCuenta (CuentaEntity cuenta) throws Exception {return cuentaRepo.save(cuenta);}

    public CuentaEntity findCuentabyId (Long id) throws Exception {return  cuentaRepo.findCuentaById(id);}

    public void deleteCuenta(Long id) throws Exception{cuentaRepo.deleteCuentaById(id); }

    /*public void deleteCuenta (Long num_cuenta) {
        cuentaRepo.deleteCuentaoById(num_cuenta);
    }*/


}

