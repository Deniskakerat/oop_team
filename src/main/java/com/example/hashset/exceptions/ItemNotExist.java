package com.example.hashset.exceptions;

public class ItemNotExist extends  Exception{
    public ItemNotExist() {
        super();
    }

    public ItemNotExist(String message) {
        super(message);
    }

    public ItemNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}