package com.coupang.marketplace.global.error;

import com.coupang.marketplace.global.common.FailResponse;
import com.coupang.marketplace.global.common.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        FailResponse res = FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FailResponse> handleIllegalArgumentException(IllegalArgumentException e){
        FailResponse res = FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<FailResponse> handleNotAuthorizedException(NotAuthorizedException e){
        FailResponse res = FailResponse.builder()
            .status(StatusEnum.UNAUTHORIZED)
            .errorMessage(e.getMessage())
            .build();
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }
}
