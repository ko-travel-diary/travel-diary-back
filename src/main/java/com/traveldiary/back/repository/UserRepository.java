package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.UserEntity;


// estate 데이터베이스의 user 테이블의 작업을 위한 리포지토리 
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

    UserEntity findByUserId (String userId);
    List<UserEntity> findByUserRoleOrderByJoinDate(String userRole);
    List<UserEntity> findByUserIdContains(String searchWord);
    List<UserEntity> findByUserRoleAndUserIdContainingOrderByJoinDate(String userRole, String searchWord);
    List<UserEntity> findByUserRole(String userRole);
    boolean existsByUserId (String userId);
    boolean existsByUserPassword (String userPassword);
    boolean existsByNickName (String nickName);
    boolean existsByUserEmail (String userEmail);

}