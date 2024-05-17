package com.traveldiary.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.repository.TourAttractionsRepository;
import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;
import com.traveldiary.back.service.TourAttractionsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourAttractionsServiceImplementation implements TourAttractionsService {

    private final TourAttractionsRepository tourAttractionsRepository;

    @Override
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList () {

        List<GetTourAttractionsResultSet> resultSets = null;

        try {
            resultSets = tourAttractionsRepository.getTourAttractionsList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetTourAttractionsListResponseDto.success(resultSets);
    }

}
