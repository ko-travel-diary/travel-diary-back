package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelCommentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TravelCommentRepository extends JpaRepository<TravelCommentEntity, Integer> {

    boolean existsByCommentNumber(Integer commentNumber);

    TravelCommentEntity findByCommentNumber(Integer commentNumber);
    List<TravelCommentEntity> findByCommentReviewNumber(Integer commentReviewNumber);
    List<TravelCommentEntity> findByCommentWriterId(String userId);

    @Transactional
    void deleteByCommentReviewNumberIn(List<Integer> commentReviewNumber);

}
