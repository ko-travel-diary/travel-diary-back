package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.QnaEntity;

import jakarta.transaction.Transactional;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity, Integer> {

    QnaEntity findByReceptionNumber(Integer receptionNumber);

    List<QnaEntity> findByOrderByReceptionNumberDesc();
    List<QnaEntity> findByQnaTitleContainsOrderByReceptionNumberDesc(String searchWord);
    List<QnaEntity> findByQnaWriterId(String userId);
    
    @Transactional
    void deleteByQnaWriterId(String qnaWriterId);

}
