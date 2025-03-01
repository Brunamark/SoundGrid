package com.soundgrid.api.domain.exception;

public class MusicNotFoundException extends RuntimeException{

    public MusicNotFoundException(String message){
        super(message);
    }
}
