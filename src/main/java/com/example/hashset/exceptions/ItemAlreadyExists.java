package com.example.hashset.exceptions;

/** Exception that thrown when we try to delete value that is not in the HashSet **/
public class ItemAlreadyExists extends  Exception{
    public ItemAlreadyExists() {
        super();
    }

    public ItemAlreadyExists(String message) {
        super(message);
    }
}
