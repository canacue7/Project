package tech.getarrays.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.exception.UserNotFoundException;
import tech.getarrays.banco.entity.UsuarioEntity;
import tech.getarrays.banco.repository.UsuarioRepo;

import java.util.List;
import java.util.UUID;


public interface UsuarioService {

    public UsuarioEntity addUsuario(UsuarioEntity usuario);

    public List<UsuarioEntity> findAllUsuarios();

    public UsuarioEntity updateUsuario(UsuarioEntity usuario);



    public UsuarioEntity findUsuarioById(Long id) throws Exception;

    public void deleteUsuario(Long id);

}

