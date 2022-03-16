package com.example.repititor.exeptions;

public class BadRequestExeption extends RuntimeException{
    public BadRequestExeption(String message) {
        super(message);
    }
}
