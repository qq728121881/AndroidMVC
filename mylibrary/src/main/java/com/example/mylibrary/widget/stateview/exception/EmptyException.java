package com.example.mylibrary.widget.stateview.exception;

public class EmptyException extends Throwable {

    public EmptyException(){
        super("暂无数据~");
    }

    public EmptyException(String message){
        super(message);
    }

}
