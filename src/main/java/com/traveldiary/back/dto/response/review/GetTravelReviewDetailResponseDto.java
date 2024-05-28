package com.traveldiary.back.dto.response.review;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TravelReviewEntity;

import lombok.Getter;

// 게시물 상세보기 Response Body Dto
@Getter
public class GetTravelReviewDetailResponseDto extends ResponseDto{

    private Integer reviewNumber;
    private String reviewTitle;
    private String reviewContent;
    private String writerId;
    private String reviewDatetime;
    private List<String> travelReviewImageUrl;
    private Integer reviewViewCount;
    private Integer reviewFavoriteCount;
    private Integer travelScheduleNumber;

    public GetTravelReviewDetailResponseDto(TravelReviewEntity travelReviewEntity, List<String> travelReviewImageUrl) {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        String writerId = travelReviewEntity.getReviewWriterId();
        
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String writeDatetime = simpleDateFormat.format(now);
        
        this.reviewNumber = travelReviewEntity.getReviewNumber();
        this.reviewTitle = travelReviewEntity.getReviewTitle();
        this.reviewContent = travelReviewEntity.getReviewContent();
        this.writerId = writerId;
        this.reviewDatetime = writeDatetime;
        this.travelReviewImageUrl = travelReviewImageUrl;
        this.reviewViewCount = travelReviewEntity.getReviewViewCount();
        this.reviewFavoriteCount = travelReviewEntity.getReviewFavoriteCount();
        this.travelScheduleNumber = travelReviewEntity.getTravelScheduleNumber();
    }

    public static ResponseEntity<GetTravelReviewDetailResponseDto> success(TravelReviewEntity travelReviewEntity, List<String> travelReviewImageUrl) {
        GetTravelReviewDetailResponseDto responseBody = new GetTravelReviewDetailResponseDto(travelReviewEntity, travelReviewImageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
