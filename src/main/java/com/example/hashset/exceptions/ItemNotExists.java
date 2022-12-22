package com.example.hashset.exceptions;

/** Exception that thrown when we try to add value that there's already in the hashSet **/
public class ItemNotExists extends Exception{
    public ItemNotExists() {
        super();
    }

    public ItemNotExists(String message) {
        super(message);
    }
}
