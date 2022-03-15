package com.example.repititor.exeptions;

public class ForbiddenExeption extends RuntimeException{
    public ForbiddenExeption(String message) {
        super(message);
    }
}
