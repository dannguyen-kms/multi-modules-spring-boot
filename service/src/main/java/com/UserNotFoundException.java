package com;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String field,String value) {
        super("No such user with "+field+": " + value);

    }
}
