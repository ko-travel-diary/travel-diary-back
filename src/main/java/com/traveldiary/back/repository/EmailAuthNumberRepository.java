package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.EmailAuthNumberEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EmailAuthNumberRepository extends JpaRepository<EmailAuthNumberEntity, String> {

    EmailAuthNumberEntity findByEmail(String email);
    boolean existsByEmailAndAuthNumber(String userEmail, String authNumber);

    @Transactional
    void deleteByEmail(String email);

}
