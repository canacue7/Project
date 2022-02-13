package tech.getarrays.banco.service;

import tech.getarrays.banco.entity.UserEntity;
import tech.getarrays.banco.entity.ClientEntity;

import java.util.List;

public interface UserService {

    public UserEntity addUser(UserEntity user)throws Exception;
    
    public void updateUser(UserEntity user)throws Exception;

    public List<UserEntity> get() throws Exception;
    
    public UserEntity findByUserName(String userName) throws Exception;

    public void delete(String userName) throws Exception;
}
