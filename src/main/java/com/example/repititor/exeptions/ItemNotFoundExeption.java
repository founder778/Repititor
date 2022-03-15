package com.example.repititor.exeptions;

public class ItemNotFoundExeption extends RuntimeException{
    public ItemNotFoundExeption(String message){
        super(message);
    }
}
