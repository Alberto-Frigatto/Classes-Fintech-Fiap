package com.services.exceptions;

public class ServiceExceptions
{
    public static abstract class ServiceException extends Exception
    {
        public ServiceException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidServiceFeeException extends ServiceException
    {
        static final String MESSAGE = "Taxa de serviço inválida";

        public InvalidServiceFeeException()
        {
            super(MESSAGE);
        }
    }
}