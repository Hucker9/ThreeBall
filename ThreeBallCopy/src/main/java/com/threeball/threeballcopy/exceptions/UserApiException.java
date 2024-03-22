package com.threeball.threeballcopy.exceptions;

public class UserApiException extends RuntimeException{
    public UserApiException(String errorMassage){
        super(errorMassage);
    }
}
