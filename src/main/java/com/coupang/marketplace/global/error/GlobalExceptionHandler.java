package com.coupang.marketplace.global.error;

import com.coupang.marketplace.auth.exception.UnauthorizedException;
import com.coupang.marketplace.global.common.FailResponse;
import com.coupang.marketplace.global.common.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public FailResponse handleIllegalArgumentException(IllegalArgumentException e){
        return FailResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .errorMessage(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public FailResponse handleUnathorizedException(UnauthorizedException e){
        return FailResponse.builder()
                .status(StatusEnum.UNAUTHORIZED)
                .errorMessage(e.getMessage())
                .build();
    }
}
