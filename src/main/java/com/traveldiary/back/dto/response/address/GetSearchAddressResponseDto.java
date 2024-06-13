package com.traveldiary.back.dto.response.address;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetSearchAddressResponseDto extends ResponseDto{

    private List<String> addresses;
    
    public GetSearchAddressResponseDto(List<String> addresses) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.addresses = addresses;
    }

    public static ResponseEntity<GetSearchAddressResponseDto> success(List<String> addresses) {
        GetSearchAddressResponseDto responseBody = new GetSearchAddressResponseDto(addresses);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
