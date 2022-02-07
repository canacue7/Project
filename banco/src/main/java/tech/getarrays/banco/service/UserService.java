package tech.getarrays.banco.service;

import tech.getarrays.banco.entity.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity addUser(UserEntity user)throws Exception;

    public List<UserEntity> get() throws Exception;

    public void delete(String userName) throws Exception;
}
