package com.Acme.game.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class DataNotFoundException extends   RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
