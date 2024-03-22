package com.threeball.threeballcopy.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String errorMassage){
        super(errorMassage);
    }
}
