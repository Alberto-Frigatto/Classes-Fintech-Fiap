package com.services.exceptions;

public class LoanExceptions
{
    public static abstract class LoanException extends Exception
    {
        public LoanException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidValueException extends LoanException
    {
        static final String MESSAGE = "Valor de empréstimo inválido";

        public InvalidValueException()
        {
            super(MESSAGE);
        }
    }
}