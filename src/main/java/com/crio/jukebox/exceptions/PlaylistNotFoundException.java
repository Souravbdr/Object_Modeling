package com.crio.jukebox.exceptions;

public class PlaylistNotFoundException extends RuntimeException{

    public PlaylistNotFoundException() {}

    public PlaylistNotFoundException(String message) {
        super(message);
    }
    
}
