package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.qna.PatchQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PatchQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaCommentRequestDto;
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
    public ResponseEntity<ResponseDto> postQna(
        @RequestBody @Valid PostQnaRequestDto responseBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaService.postQna(responseBody, userId);
        return response;
    }

    @PostMapping("/{receptionNumber}/comment")
    public ResponseEntity<ResponseDto> postQnaComment(
        @RequestBody @Valid PostQnaCommentRequestDto requestBody,
        @PathVariable("receptionNumber") Integer receptionNumber,
        @AuthenticationPrincipal String userID
    ) {
        ResponseEntity<ResponseDto> response = qnaService.postQnaComment(requestBody, receptionNumber) ;
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaList() {
        ResponseEntity<? super GetQnaBoardResponseDto> response = qnaService.getQnaList();
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<? super GetSearchQnaBoardResponseDto> getSearchQnaList(
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetSearchQnaBoardResponseDto> response = qnaService.getSearchQnaList(word);
        return response;
    }

    @GetMapping("/{receptionNumber}")
    public ResponseEntity<? super GetQnaResponseDto> getQna(
        @PathVariable("receptionNumber") Integer receptionNumber
    ) {
        ResponseEntity<? super GetQnaResponseDto> response = qnaService.getQnaBoard(receptionNumber);
        return response;
    }

    @PatchMapping("/{receptionNumber}")
    public ResponseEntity<ResponseDto> patchQna(
        @RequestBody @Valid PatchQnaRequestDto requestBody,
        @PathVariable("receptionNumber") Integer receptionNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaService.patchQna(requestBody, receptionNumber, userId);
        return response;
    }

    @PatchMapping("/{receptionNumber}/comment")
    public ResponseEntity<ResponseDto> patchQnaComment(
        @RequestBody @Valid PatchQnaCommentRequestDto requstBody,
        @PathVariable("receptionNumber") Integer receptionNumber
    ) {
        ResponseEntity<ResponseDto> response = qnaService.patchQnaComment(requstBody, receptionNumber);
        return response;
    }

    @DeleteMapping("/{receptionNumber}")
    public ResponseEntity<ResponseDto> deleteQna(
        @PathVariable("receptionNumber") Integer receptionNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaService.deleteQna(receptionNumber, userId);
        return response;
    }

    @DeleteMapping("/{receptionNumber}/comment")
    public ResponseEntity<ResponseDto> deleteQnaComment(
        @PathVariable("receptionNumber") Integer receptionNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaService.deleteQnaComment(receptionNumber, userId);
        return response;
    }
    
}
