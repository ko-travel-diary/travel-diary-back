package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.repository.resultSet.GetTravelReviewResultSet;

@Repository
public interface TravelReviewRepository extends JpaRepository<TravelReviewEntity, Integer>{

    @Query(
        value=
        "SELECT image, " + 
                "r.review_number as reviewNumber, " +
                "r.review_title as reviewTitle, " +
                "r.review_content as reviewContent, " + 
                "r.review_writer_id as reviewWriterId, " +
                "r.review_datetime as reviewDatetime, " +
                "r.review_view_count as reviewViewCount, " +
                "r.review_favorite_count as reviewFavoriteCount FROM travel_review r LEFT JOIN ( " +
            "SELECT travel_review_number, ANY_VALUE(travel_review_image_url) as image " +

            
            "FROM travel_review_image " +
            "GROUP BY travel_review_number " +
        ") i " +
        "ON r.review_number = i.travel_review_number ",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewBoardList();

    @Query(
        value=
        "SELECT image, " + 
                "r.review_number as reviewNumber, " +
                "r.review_title as reviewTitle, " +
                "r.review_content as reviewContent, " + 
                "r.review_writer_id as reviewWriterId, " +
                "r.review_datetime as reviewDatetime, " +
                "r.review_view_count as reviewViewCount, " +
                "r.review_favorite_count as reviewFavoriteCount FROM travel_review r LEFT JOIN ( " +
            "SELECT travel_review_number, ANY_VALUE(travel_review_image_url) as image " +
            "FROM travel_review_image " +
            "GROUP BY travel_review_number " +
        ") i " +
        "ON r.review_number = i.travel_review_number " +
        "WHERE r.review_title OR r.review_content LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReivewTitleOrReviewContent (String searchWord);
    @Query(
        value=
        "SELECT image, " + 
                "r.review_number as reviewNumber, " +
                "r.review_title as reviewTitle, " +
                "r.review_content as reviewContent, " + 
                "r.review_writer_id as reviewWriterId, " +
                "r.review_datetime as reviewDatetime, " +
                "r.review_view_count as reviewViewCount, " +
                "r.review_favorite_count as reviewFavoriteCount FROM travel_review r LEFT JOIN ( " +
            "SELECT travel_review_number, ANY_VALUE(travel_review_image_url) as image " +
            "FROM travel_review_image " +
            "GROUP BY travel_review_number " +
        ") i " +
        "ON r.review_number = i.travel_review_number " +
        "WHERE r.review_writer_id LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewWriter (String searchWord);
    @Query(
        value=
        "SELECT image, " + 
                "r.review_number as reviewNumber, " +
                "r.review_title as reviewTitle, " +
                "r.review_content as reviewContent, " + 
                "r.review_writer_id as reviewWriterId, " +
                "r.review_datetime as reviewDatetime, " +
                "r.review_view_count as reviewViewCount, " +
                "r.review_favorite_count as reviewFavoriteCount FROM travel_review r LEFT JOIN ( " +
            "SELECT travel_review_number, ANY_VALUE(travel_review_image_url) as image " +
            "FROM travel_review_image " +
            "GROUP BY travel_review_number " +
        ") i " +
        "ON r.review_number = i.travel_review_number " +
        "WHERE r.review_datetime LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetTravelReviewResultSet> getReviewDatetime (String searchWord);

    boolean existsByReviewNumber(Integer reviewNumber);

    TravelReviewEntity findByReviewNumber(Integer reviewNumber);
    List<TravelReviewEntity> findByOrderByReviewNumberDesc();
    List<TravelReviewEntity> findByReviewWriterId(String userId);
}
