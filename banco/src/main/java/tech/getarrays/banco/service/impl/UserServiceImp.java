package tech.getarrays.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tech.getarrays.banco.entity.UserEntity;
import tech.getarrays.banco.entity.UsuarioEntity;
import tech.getarrays.banco.exception.UserNotFoundException;
import tech.getarrays.banco.repository.UserRepo;
import tech.getarrays.banco.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepo userRepo;


    @Autowired
    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity addUser(UserEntity user){
       // manager.setUsuario_codigo(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    public List<UserEntity> get(){
        return userRepo.findAll();
    }

    public void delete(String userName){userRepo.deleteByUserName(userName); }
    
    @Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userVO = userRepo.findByUserName(userName);
		UserDetails userDetails = new User(userVO.getUserName(), userVO.getPassword(), new ArrayList<>());
		
		return userDetails;
	}


}
