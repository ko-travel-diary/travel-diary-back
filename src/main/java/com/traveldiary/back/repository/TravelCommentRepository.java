package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelCommentEntity;

@Repository
public interface TravelCommentRepository extends JpaRepository<TravelCommentEntity, Integer>{

    TravelCommentEntity findByCommentNumber(Integer commentNumber);
    List<TravelCommentEntity> findByCommentReviewNumber(Integer commentReviewNumber);
    List<TravelCommentEntity> findByCommentWriterId(String userId);
    boolean existsByCommentNumber(Integer commentNumber);

}
