package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaBoardResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaResponseDto;
import com.traveldiary.back.dto.response.qna.GetSearchQnaBoardResponseDto;
import com.traveldiary.back.service.QnaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postQna (
        @RequestBody @Valid PostQnaRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaService.postQna(responseBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaList () {
        ResponseEntity<? super GetQnaBoardResponseDto> response = qnaService.getQnaList();
        return response;
    }

    @GetMapping("/list/search")
    public ResponseEntity<? super GetSearchQnaBoardResponseDto> getSearchQnaList (
        @RequestParam("searchWord") String searchWord
    ) {
        ResponseEntity<? super GetSearchQnaBoardResponseDto> response = qnaService.getSearchQnaList(searchWord);
        return response;
    }

    @GetMapping("/list/{receptionNumber}")
    public ResponseEntity<? super GetQnaResponseDto> getQna (
        @PathVariable("receptionNumber") Integer receptionNumber
    ) {
        ResponseEntity<? super GetQnaResponseDto> response = qnaService.getQnaBoard(receptionNumber);
        return response;
    }
    
}
