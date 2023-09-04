package com.services.exceptions;

public class ServiceExceptions
{
    public static abstract class ServiceException extends Exception
    {
        public ServiceException(String message)
        {
            super(message);
        }
    }

    public static class InvalidServiceFeeException extends ServiceException
    {
        static String MESSAGE = "\nTaxa de serviço inválida\n";

        public InvalidServiceFeeException()
        {
            super(MESSAGE);
        }
    }
}