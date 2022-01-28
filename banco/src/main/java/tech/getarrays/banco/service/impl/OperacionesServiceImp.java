package tech.getarrays.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.entity.OperacionesEntity;
import tech.getarrays.banco.repository.OperacionesRepo;
import tech.getarrays.banco.service.OperacionesService;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class OperacionesServiceImp implements OperacionesService {

    private final OperacionesRepo operacionesRepo;

    @Autowired
    public OperacionesServiceImp(OperacionesRepo operacionesRepo){this.operacionesRepo= operacionesRepo; }

    public OperacionesEntity addOperaciones(OperacionesEntity operaciones) throws Exception{
        return operacionesRepo.save(operaciones);
    }

    public List<OperacionesEntity> findOpsByCuentaId (Long id) throws Exception{
        return operacionesRepo.findOpsByIdCuenta(id);
    }


}