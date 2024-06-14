package com.traveldiary.back.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.traveldiary.back.dto.response.ResponseDto;
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ResponseDto> validationExceptionHandler (
        Exception exception
    ) {
        exception.printStackTrace();
        return ResponseDto.validationFailed();
    }

    @ExceptionHandler(
        NoHandlerFoundException.class
    )
    public ResponseEntity<ResponseDto> noHandlerFoundException (
        Exception exception
    ){
        exception.printStackTrace();
        return ResponseDto.notFound();
    }

}