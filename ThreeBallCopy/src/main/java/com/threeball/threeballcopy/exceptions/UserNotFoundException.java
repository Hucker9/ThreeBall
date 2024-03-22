package com.threeball.threeballcopy.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMassage){
        super(errorMassage);
    }
}
