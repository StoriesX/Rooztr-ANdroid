package com.rooztr.exceptions;

/**
 * Created by ed12al on 8/14/2016.
 */
public class InvalidInputException extends Exception{
    public InvalidInputException(){
    }

    @Override
    public String getMessage(){
        return "invalid input";
    }

}
