package com.brianbrix.demo.exceptions;

public class ServiceException extends RuntimeException{
    private static int statusCode;
    public ServiceException(String message, int statusCode)
    {
        super(message);
        this.statusCode= statusCode;
    }

    public static int getStatusCode() {
        return statusCode;
    }
}
