package com.crio.jukebox.exceptions;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super(message);
    }
    
}
