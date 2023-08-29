package com.crio.jukebox.exceptions;

public class NoSuchCommandException extends RuntimeException{

    public NoSuchCommandException() {}

    public NoSuchCommandException(String message) {
        super(message);
    }
    
}
