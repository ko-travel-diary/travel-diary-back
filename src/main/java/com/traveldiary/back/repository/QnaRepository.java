package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.QnaEntity;
import com.traveldiary.back.entity.RestaurantRecommendEntity;

import jakarta.transaction.Transactional;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity, Integer> {

    List<QnaEntity> findByOrderByReceptionNumberDesc();
    List<QnaEntity> findByQnaTitleContainsOrderByReceptionNumberDesc(String searchWord);
    List<QnaEntity> findByQnaWriterId(String userId);
    QnaEntity findByReceptionNumber(Integer receptionNumber);

    @Transactional
    void deleteByQnaWriterId(String qnaWriterId);

}
