package tech.getarrays.banco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tech.getarrays.banco.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {

    void deleteByUserName(String userName);
    
    UserEntity findByUserName(String userName);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_entity SET first_name=?1, last_name = ?2 WHERE user_name =?3", nativeQuery = true)
    void updateByUserName(String first_name, String last_name, String user_name);
}
