package tech.getarrays.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.entity.OperacionesEntity;
import tech.getarrays.banco.repository.OperacionesRepo;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public interface OperacionesService {

    public OperacionesEntity addOperaciones(OperacionesEntity operaciones) throws Exception;

   public List<OperacionesEntity> findOpsByCuentaId (Long id) throws Exception;



}
