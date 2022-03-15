package com.example.repititor.exeptions;

public class UnauthorizaedExeption extends RuntimeException{
    public UnauthorizaedExeption(String message) {
        super(message);
    }
}
