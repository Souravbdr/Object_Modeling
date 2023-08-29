package com.crio.jukebox.exceptions;

public class UnAuthorizedException extends RuntimeException{

    public UnAuthorizedException() {}

    public UnAuthorizedException(String message) {
        super(message);
    }
    
}
