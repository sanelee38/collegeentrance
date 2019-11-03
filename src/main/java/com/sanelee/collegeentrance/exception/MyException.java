package com.sanelee.collegeentrance.exception;

public class MyException extends RuntimeException {
    private static  final long serialVersionId = 1L;
    public MyException(String message){
        super(message);
    }
}
