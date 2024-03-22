package com.threeball.threeballcopy.exceptions;

public class WrongEmailException extends RuntimeException{
    public WrongEmailException(String errorMassage){
        super(errorMassage);
    }
}
