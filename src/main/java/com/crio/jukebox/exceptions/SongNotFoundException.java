package com.crio.jukebox.exceptions;

public class SongNotFoundException extends RuntimeException{

    public SongNotFoundException() {}

    public SongNotFoundException(String message) {
        super(message);
    }
    
}
