package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewViewEntity;
import com.traveldiary.back.repository.resultSet.GetTravelReviewResultSet;

@Repository
public interface TravelReviewViewRepository extends JpaRepository<TravelReviewViewEntity, Integer>{
    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewBoardList();

    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view " +
            "WHERE r.review_title LIKE %:searchWord% OR r.review_content LIKE %:searchWord%  ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReivewTitleOrReviewContent (@Param("searchWord") String searchWord);

    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view " +
            "WHERE r.review_writer_id LIKE %:searchWord%  ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewWriter (@Param("searchWord") String searchWord);

    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view " +
            "WHERE r.review_datetime LIKE %:searchWord%  ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewDatetime (@Param("searchWord") String searchWord);

    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view " +
            "WHERE r.review_writer_id = :userId  ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet>findByReviewWriterIdOrderByReviewNumberDesc(String userId);

    @Query(
        value=
            "SELECT * " +
            "FROM travel_diary.travel_review_view " +
        "WHERE r.review_title LIKE %:searchWord%  ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> findByReviewTitleContainsOrderByReviewNumberDesc(String searchWord);

}
