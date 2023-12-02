package com.globalException.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)

    public ResponseEntity<ExceptionResponce> handleException(RecordNotFoundException recNotFound) {

        ExceptionResponce exResponce   = new ExceptionResponce();
        exResponce.setMsg("RECORD NOT FOUND");
        exResponce.setLocalDateTime(LocalDateTime.now());
        ResponseEntity<ExceptionResponce> entity  = new ResponseEntity<>(exResponce, HttpStatus.NOT_FOUND);
        return entity;



    }
}
