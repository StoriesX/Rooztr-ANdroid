package com.rooztr.exceptions;

/**
 * Created by ed12al on 8/14/2016.
 */
public class UnauthenticatedException extends Exception{
    public UnauthenticatedException(){}

    @Override
    public String getMessage(){
        return "Unauthenticated";
    }
}
