package com.example.mylibrary.frame.mvp.presenter;

public class FrameNotReadyException extends RuntimeException {

    public FrameNotReadyException(){
        super("Frame Not Ready");
    }

    public FrameNotReadyException(String message){
        super(message);
    }
}
