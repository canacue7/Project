package tech.getarrays.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.exception.UserNotFoundException;
import tech.getarrays.banco.entity.UsuarioEntity;
import tech.getarrays.banco.repository.UsuarioRepo;
import tech.getarrays.banco.service.UsuarioService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioServiceImp(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public UsuarioEntity addUsuario(UsuarioEntity usuario){
        usuario.setUsuario_codigo(UUID.randomUUID().toString());
        return usuarioRepo.save(usuario);
    }

    public List<UsuarioEntity> findAllUsuarios(){
        return usuarioRepo.findAll();
    }

    public UsuarioEntity updateUsuario(UsuarioEntity usuario){
        return  usuarioRepo.save(usuario);
    }



    public UsuarioEntity findUsuarioById(Long id) throws  Exception{
        return usuarioRepo.findUsuarioById(id).orElseThrow(() -> new UserNotFoundException("Usuario con id: " + id +" no fue encontrado"));
    }

    public void deleteUsuario(Long id){usuarioRepo.deleteUsuarioById(id); }

}

