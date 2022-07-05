package net.javaguides.springboot.exception;

public class APIException extends RuntimeException{

    public APIException(String message){

        super(message);
    }
}
