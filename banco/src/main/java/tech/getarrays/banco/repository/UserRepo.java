package tech.getarrays.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.banco.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {

    void deleteByUserName(String userName);
    
    UserEntity findByUserName(String userName);
}
