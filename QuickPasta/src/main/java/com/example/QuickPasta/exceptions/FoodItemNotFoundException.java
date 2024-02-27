package com.example.QuickPasta.exceptions;

public class FoodItemNotFoundException extends RuntimeException{
    public FoodItemNotFoundException(String msg) {
        super(msg);
    }
}
