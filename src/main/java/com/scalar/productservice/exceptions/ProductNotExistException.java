package com.scalar.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProductNotExistException extends Exception{

    //private String msg;

    public ProductNotExistException(String message){
        super(message);
    }
}
