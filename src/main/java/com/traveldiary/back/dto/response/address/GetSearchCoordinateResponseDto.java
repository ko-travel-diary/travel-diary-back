package com.traveldiary.back.dto.response.address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetSearchCoordinateResponseDto extends ResponseDto{

    private Double x;
    private Double y;

    private GetSearchCoordinateResponseDto(Double x, Double y) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.x = x;
        this.y = y;
    }

    public static ResponseEntity<GetSearchCoordinateResponseDto> success(Double x, Double y) {
        GetSearchCoordinateResponseDto responseBody = new GetSearchCoordinateResponseDto(x, y);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
