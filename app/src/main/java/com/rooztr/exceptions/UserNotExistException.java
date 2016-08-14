package com.rooztr.exceptions;

/**
 * Created by ed12al on 8/14/2016.
 */
public class UserNotExistException extends Exception{
    public UserNotExistException(){
    }

    @Override
    public String getMessage(){
        return "User does not exist in the users database";
    }
}
