package com.example.hashset.exceptions;

public class ItemAlreadyExists extends  Exception{
    public ItemAlreadyExists() {
        super();
    }

    public ItemAlreadyExists(String message) {
        super(message);
    }

    public ItemAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
