package com;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String field,String value) {
        super(String.format("No such user with %s: %s",field,value));

    }
}
