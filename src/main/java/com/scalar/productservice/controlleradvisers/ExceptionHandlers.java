package com.scalar.productservice.controlleradvisers;

import com.scalar.productservice.dtos.ExceptionsDto;
import com.scalar.productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotExistException.class )
    public ResponseEntity<ExceptionsDto> handleProductException(ProductNotExistException exception){

        ExceptionsDto dto=new ExceptionsDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
